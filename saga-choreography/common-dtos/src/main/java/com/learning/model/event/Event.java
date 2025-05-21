package com.learning.model.event;

import java.util.Date;
import java.util.UUID;

public interface Event {

    UUID getUUID();
    Date getDate();
}
