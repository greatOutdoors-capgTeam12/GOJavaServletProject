package com.capgemini.go.controller;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/*
 * Remember, the attribute names in the Bean Class must match those that are sent from remote server in the json object
 * The below is the form used in the angular component
 * deliveryTimeReportForm = this.fb.group({
    retailerId: [''],
    reportType: [''],
    startDate: [''],
    endDate: ['']
  });
 */
@XmlRootElement
public class ReportFormBean {
    @XmlElement public String retailerId;
    @XmlElement public String reportType;
    @XmlElement public String startDate;
    @XmlElement public String endDate;
    @XmlElement public Double bonus;
    @XmlElement public Double target;
}

