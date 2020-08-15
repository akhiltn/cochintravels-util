package com.cochintravels.until.model;

import lombok.Data;
import lombok.ToString;

import java.time.OffsetDateTime;

@Data
public class BookingForm{
        private String firstName;
        private String lastName;
        private String email;
        private String mobile;
        private String serviceRequested;
        private String startDate;
        private String endDate;
        private String additionalDetails;
        @ToString.Exclude
        private String recaptchaReactive;
}