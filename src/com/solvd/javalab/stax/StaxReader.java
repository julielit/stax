package com.solvd.javalab.stax;

import org.xml.sax.XMLReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.apache.log4j.Logger;

public class StaxReader {

    private static final Logger LOG = Logger.getLogger(StaxReader.class);

    public static void main(String[] args) {
        String fileName = "user.xml";
        StaxReader reader = new StaxReader();
        List<User> usrList = reader.parseXML(fileName);
        for (User usr : usrList) {
            LOG.info(usr.toString());
        }
    }

    private List<User> parseXML(String fileName) {
        List<User> usrList = new ArrayList<>();
        User usr = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("User")) {
                        usr = new User();
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        usr.setId(Integer.parseInt(idAttr.getValue()));
                    }
                    else if (startElement.getName().getLocalPart().equals("firstname")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        usr.setFname(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("lastname")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        usr.setLname(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("email")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        usr.setEmail(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("phonenum")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        usr.setPhone(xmlEvent.asCharacters().getData());
                    }

                    if (startElement.getName().getLocalPart().equals("Address")) {
                        Address nextAddress = this.readAddress(startElement, xmlEventReader);
                        if (usr.getAddresses() == null) {
                            usr.setAddresses(new ArrayList<Address>());
                        }
                        usr.getAddresses().add(nextAddress);
                    }
                }

                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("User")) {
                        usrList.add(usr);
                    }
                }
            }
        } catch(FileNotFoundException | XMLStreamException e){
            LOG.error(e);
        }
        return usrList;
    }

    private Address readAddress(StartElement startElement, XMLEventReader xmlEventReader) throws XMLStreamException {
        Address addr = new Address();
        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
        addr.setId(Integer.parseInt(idAttr.getValue()));
        while (xmlEventReader.hasNext()) {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
            if (xmlEvent.isStartElement()) {
                StartElement startSubElement = xmlEvent.asStartElement();
                if (startSubElement.getName().getLocalPart().equals("countryname")) {
                    xmlEvent = xmlEventReader.nextEvent();
                    addr.setCountry(xmlEvent.asCharacters().getData());
                } else if (startSubElement.getName().getLocalPart().equals("cityname")) {
                    xmlEvent = xmlEventReader.nextEvent();
                    addr.setCity(xmlEvent.asCharacters().getData());
                } else if (startSubElement.getName().getLocalPart().equals("addressline")) {
                    xmlEvent = xmlEventReader.nextEvent();
                    addr.setAddressLine(xmlEvent.asCharacters().getData());
                }
            } else if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals("Address")) {
                    break;
                }
            }
        }
        return addr;
    }
}
