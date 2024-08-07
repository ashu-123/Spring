package com.learning.trackzilla.controller;

import com.learning.trackzilla.model.Application;
import com.learning.trackzilla.model.Release;
import com.learning.trackzilla.model.Ticket;
import com.learning.trackzilla.model.User;
import com.learning.trackzilla.repositories.ApplicationRepository;
import com.learning.trackzilla.repositories.TicketRepository;
import com.learning.trackzilla.service.ApplicationService;
import com.learning.trackzilla.service.ReleaseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/tza")
public class TzaController {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ReleaseService releaseService;

    // ************** Methods for Applications *************************

    //Repository Methods
    @RequestMapping(value = "/applications", method = RequestMethod.GET)
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @RequestMapping(value = "/applications/{id}", method = RequestMethod.GET)
    public Optional<Application> getApplicationById(@PathVariable("id") String id) {
        return applicationRepository.findById(id);
    }

    @RequestMapping(value = "/applications", method = RequestMethod.POST)
    public Application addNewApplication(@RequestBody Application application){
        return applicationRepository.save(application);
    }

    @RequestMapping(value = "/applications/{id}", method = RequestMethod.PUT)
    public Application updateApplication(@PathVariable("id") String id, @RequestBody Application application){
        application.setId(id);
        return applicationRepository.save(application);
    }

    @RequestMapping(value = "/applications/{id}", method = RequestMethod.DELETE)
    public void deleteApplication(@PathVariable("id") String id) {
        applicationRepository.deleteById(id);
    }

    @RequestMapping(value = "/applications/name/{name}", method = RequestMethod.GET)
    public List<Application> findByName(@PathVariable("name") String name) {
        return applicationRepository.findByName(name);
    }

    //MongoTemplate Methods
    @RequestMapping(value = "/applications/template", method = RequestMethod.POST)
    public void addNewApplicationWTemplate(@RequestBody Application application){
        applicationService.addNewApplicationWTemplate(application);
    }

    @RequestMapping(value = "/applications/template/name/{id}", method = RequestMethod.GET)
    public Application findByIdTemplate(@PathVariable("id") String id){
        return applicationService.findByIdTemplate(id);
    }

    @RequestMapping(value = "/applications/template", method = RequestMethod.DELETE)
    public void deleteWTemplate(@RequestBody Application application){
        applicationService.deleteWTemplate(application);
    }

    @RequestMapping(value = "/applications/template", method = RequestMethod.PUT)
    public void updateApplicationWTemplate(@RequestBody Application application){
        applicationService.updateApplicationWTemplate(application);
    }


    // ************** Methods for Tickets *************************
    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.GET)
    public Optional<Ticket> getTicketById(@PathVariable("id") String id) {
        return ticketRepository.findById(id);
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    public Ticket addNewApplication(@RequestBody Ticket ticket){
        return ticketRepository.save(ticket);
    }

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.PUT)
    public Ticket updateApplication(@PathVariable("id") String id, @RequestBody Ticket ticket){
        ticket.setId(id);
        return ticketRepository.save(ticket);
    }

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.DELETE)
    public void deleteTicket(@PathVariable("id") String id) {
        ticketRepository.deleteById(id);
    }

    @RequestMapping(value = "/tickets/status/{status}", method = RequestMethod.GET)
    public List<Ticket> findByStatus(@PathVariable("status") String status) {
        return ticketRepository.findByStatus(status);
    }

    @RequestMapping(value = "/tickets/appId/{appId}", method = RequestMethod.GET)
    public List<Ticket> findByAppId(@PathVariable("appId") String appId) {
        return ticketRepository.findByAppId(appId);
    }

    @RequestMapping(value = "/tickets/count", method = RequestMethod.GET)
    public Long countAllTickets() {
        Stream<Ticket> stream = ticketRepository.findAllByCustomQueryAndStream("Open");
        Long count = stream.count();
        stream.close();
        return count;
    }

    // ************** Methods for Releases *************************
    @RequestMapping(value = "/releases", method = RequestMethod.GET)
    public List<Release> getAllReleases() {
        return releaseService.findAll();
    }

    @RequestMapping(value = "/releases/{id}", method = RequestMethod.GET)
    public Optional<Release> getReleaseId(@PathVariable("id") String id) {
        return releaseService.findById(id);
    }

    @RequestMapping(value = "/releases", method = RequestMethod.POST)
    public Release addNewRelease(@RequestBody Release release){
        return releaseService.save(release);
    }

    @RequestMapping(value = "/releases/{id}", method = RequestMethod.PUT)
    public Release updateRelease(@PathVariable("id") String id, @RequestBody Release release){
        release.setId(id);
        return releaseService.save(release);
    }

    @RequestMapping(value = "/releases/{id}", method = RequestMethod.DELETE)
    public void deleteRelease(@PathVariable("id") String id) {
        releaseService.deleteById(id);
    }

    @RequestMapping(value ="/releases/tickets", method = RequestMethod.PUT)
    public void addNewReleaseWTickets(@RequestBody Release release) {
        releaseService.insert(release);
    }

    @RequestMapping(value = "/releases/status/{status}", method = RequestMethod.GET)
    public List<Release> getReleaseByTicketStatus(@PathVariable("status") String status){
        return releaseService.getReleaseByTicketStatus(status);
    }

    //************ Methods for User Session****************************
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public User addUserToSession(@RequestBody User user, HttpSession session) {
        session.setAttribute("USER", user);
        return user;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object getUserFromSession(HttpSession session){
        return session.getAttribute("USER");
    }

    //************* Transaction Management *****************************
    @RequestMapping(value = "retire/application", method = RequestMethod.DELETE)
    public void retireApplication(@RequestBody Application application) {  //just pass in the Id
        applicationService.retireApplication(application);
    }

}
