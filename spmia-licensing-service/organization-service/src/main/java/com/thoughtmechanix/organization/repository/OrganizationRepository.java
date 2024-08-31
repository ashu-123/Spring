package com.thoughtmechanix.organization.repository;

import com.thoughtmechanix.organization.model.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, String> {

    Organization findById(String id);
}
