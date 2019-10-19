package com.capgemini.go.controller;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SalesRepFormBean {
	@XmlElement public String userId;
	@XmlElement public Double setBonus;
    @XmlElement public Double setTarget;
}
