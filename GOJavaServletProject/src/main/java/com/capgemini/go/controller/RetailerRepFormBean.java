package com.capgemini.go.controller;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RetailerRepFormBean {
	@XmlElement public String userId;
	@XmlElement public Double setDiscount;
}
