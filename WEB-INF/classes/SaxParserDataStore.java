
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a �de facto� standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
    public DoorBells doorBell;
    public DoorLocks doorLock;
    public Lightings lighting;
    public Speakers speaker;
    public Thermostats thermostat;
	public Accessory accessory;
	HashMap<String,Object> products;
    static HashMap<String,DoorBells> doorbells;
    static HashMap<String,DoorLocks> doorlocks;
    static HashMap<String,Lightings> lightings;
    static HashMap<String,Speakers> speakers;
    static HashMap<String,Thermostats> thermostats;

   	static HashMap<String,Accessory> accessories;
	String tvXmlFileName;
	int totalSize;
	HashMap<String,String> accessoryHashMap;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String tvXmlFileName) {
    this.tvXmlFileName = tvXmlFileName;
    doorbells = new HashMap<String, DoorBells>();
	doorlocks = new  HashMap<String, DoorLocks>();
	lightings = new HashMap<String, Lightings>();
	speakers = new HashMap<String, Speakers>();
	thermostats = new HashMap<String, Thermostats>();
	accessories=new HashMap<String, Accessory>();
	accessoryHashMap=new HashMap<String,String>();
	products = new HashMap<String, Object>();
	parseDocument();


	products.put("doorbells",doorbells);
	products.put("doorlocks",doorlocks);
	products.put("lightings",lightings);
	products.put("speakers",speakers);
	products.put("thermostats",thermostats);
	products.put("accessories",accessories);

	totalSize=doorbells.size()+doorlocks.size()+lightings.size()+speakers.size()+thermostats.size()+accessories.size();
    
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(tvXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}

	public HashMap<String,Object> getProducts()
	{
		return products;
	}
	
	public int getProductsSize()
	{
		return totalSize;
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for tv,sounds etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("doorbells")) 
		{
			currentElement="doorbells";
			doorBell = new DoorBells();
            doorBell.setId(attributes.getValue("id"));
		}
        if (elementName.equalsIgnoreCase("doorlocks"))
		{
			currentElement="doorlocks";
			doorLock = new DoorLocks();
            doorLock.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("lightings"))
		{
			currentElement="lightings";
			lighting = new Lightings();
            lighting.setId(attributes.getValue("id"));
        }
         if (elementName.equalsIgnoreCase("speakers"))
		{
			currentElement="speakers";
			speaker = new Speakers();
            speaker.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("thermostats"))
		{
			currentElement="thermostats";
			thermostat= new Thermostats();
            thermostat.setId(attributes.getValue("id"));
        }
        if (elementName.equals("accessory")) //&&  !currentElement.equals("tv"))
		{
			currentElement="accessory";
			accessory=new Accessory();
			accessory.setId(attributes.getValue("id"));
	    }


    }
	// when xml end element is parsed store the data into respective hashmap for tv,sounds etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("doorbells")) {
			doorbells.put(doorBell.getId(),doorBell);
			return;
        }
 
        if (element.equals("doorlocks")) {	
			doorlocks.put(doorLock.getId(),doorLock);
			return;
        }
        if (element.equals("lightings")) {	
			lightings.put(lighting.getId(),lighting);
			return;
        }
         if (element.equals("speakers")) {	
			speakers.put(speaker.getId(),speaker);
			return;
        }
        if (element.equals("thermostats")) {	  
			thermostats.put(thermostat.getId(),thermostat);
			return;
        }
        if (element.equals("accessory") && currentElement.equals("accessory")) {
			accessories.put(accessory.getId(),accessory);       
			return; 
        }
		/*
		if (element.equals("accessory") && currentElement.equals("tv")) 
		{
			accessoryHashMap.put(elementValueRead,elementValueRead);
		}
      	if (element.equalsIgnoreCase("accessories") && currentElement.equals("tv")) {
			tv.setAccessories(accessoryHashMap);
			accessoryHashMap=new HashMap<String,String>();
			return;
		}
		*/
        if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equals("doorbells"))
				doorBell.setImage(elementValueRead);
        	if(currentElement.equals("doorlocks"))
				doorLock.setImage(elementValueRead);
            if(currentElement.equals("lightings"))
				lighting.setImage(elementValueRead);
			 if(currentElement.equals("speakers"))
				speaker.setImage(elementValueRead);
			if(currentElement.equals("thermostats"))
			    thermostat.setImage(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setImage(elementValueRead);          
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("doorbells"))
				doorBell.setDiscount(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("doorlocks"))
				doorLock.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("lightings"))
				lighting.setDiscount(Double.parseDouble(elementValueRead));
			 if(currentElement.equals("speakers"))
				speaker.setDiscount(Double.parseDouble(elementValueRead));
			 if(currentElement.equals("thermostats"))
			    thermostat.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setDiscount(Double.parseDouble(elementValueRead));          
			return;
	    }


		if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("doorbells"))
				doorBell.setCondition(elementValueRead);
        	if(currentElement.equals("doorlocks"))
				doorLock.setCondition(elementValueRead);
            if(currentElement.equals("lightings"))
				lighting.setCondition(elementValueRead);
			if(currentElement.equals("speakers"))
				speaker.setCondition(elementValueRead);
			if(currentElement.equals("thermostats"))
				thermostat.setCondition(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setCondition(elementValueRead);          
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equals("doorbells"))
				doorBell.setRetailer(elementValueRead);
        	if(currentElement.equals("doorlocks"))
				doorLock.setRetailer(elementValueRead);
            if(currentElement.equals("lightings"))
				lighting.setRetailer(elementValueRead);
			if(currentElement.equals("speakers"))
			 	speaker.setRetailer(elementValueRead);
			if(currentElement.equals("thermostats"))
				thermostat.setRetailer(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setRetailer(elementValueRead);          
			return;
		}

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("doorbells"))
				doorBell.setName(elementValueRead);
        	if(currentElement.equals("doorlocks"))
				doorLock.setName(elementValueRead);
            if(currentElement.equals("lightings"))
				lighting.setName(elementValueRead);
			if(currentElement.equals("speakers"))
			 	speaker.setName(elementValueRead);
			if(currentElement.equals("thermostats"))
				thermostat.setName(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setName(elementValueRead);          
			return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equals("doorbells"))
				doorBell.setPrice(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("doorlocks"))
				doorLock.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("lightings"))
				lighting.setPrice(Double.parseDouble(elementValueRead));
			 if(currentElement.equals("speakers"))
				speaker.setPrice(Double.parseDouble(elementValueRead));
			 if(currentElement.equals("thermostats"))
			 	thermostat.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setPrice(Double.parseDouble(elementValueRead));          
			return;
		}

		if (element.equalsIgnoreCase("description")) {
            if(currentElement.equals("doorbells"))
				doorBell.setDescription(elementValueRead);
        	if(currentElement.equals("doorlocks"))
				doorLock.setDescription(elementValueRead);
            if(currentElement.equals("lightings"))
				lighting.setDescription(elementValueRead);
			if(currentElement.equals("speakers"))
				speaker.setDescription(elementValueRead);
			if(currentElement.equals("thermostats"))
			 	thermostat.setDescription(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setDescription(elementValueRead);          
			return;
	    }
		
		

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\Assignment_3\\ProductCatalog.xml");
    } 
}
