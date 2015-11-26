package com.cathalus.javasplitter.files;

import com.cathalus.javasplitter.model.Run;
import com.cathalus.javasplitter.model.Segment;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

/**
 * Created by Raymond on 26.11.2015.
 */
public class RunParser {

    private Element documentRoot;
    private Run parsedRun;
    private Element[] empty = new Element[0];

    public RunParser(InputStream input)
    {
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(input);
            documentRoot = document.getDocumentElement();
            if(!documentRoot.getNodeName().equals("Run"))
            {
                // TODO(raymond): debug class implementation
                // TODO(raymond): real error handling
                System.out.println("NOT A VALID RUN FILE!");
            }
            // Parse Game Name
            String gameName = documentRoot.getElementsByTagName("GameName")
                                          .item(0)
                                          .getTextContent();
            parsedRun = new Run(gameName);
            // Parse Attempt Count
            int attemptCount = Integer.parseInt(documentRoot.getElementsByTagName("AttemptCount")
                                                            .item(0)
                                                            .getTextContent());
            parsedRun.setAttempts(attemptCount);

            // Parse Run Category
            String runCategory = documentRoot.getElementsByTagName("CategoryName")
                                             .item(0)
                                             .getTextContent();
            parsedRun.setCategory(runCategory);

            for(Element e : getElementsByTagName(documentRoot,"Segments"))
            {
                for(Element segment : getElementsByTagName(e,"Segment"))
                {
                    parseSegment(segment);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseSegment(Element e)
    {
        String segmentName = e.getElementsByTagName("Name").item(0).getTextContent();
        String splitTime = e.getElementsByTagName("SplitTime").item(0).getTextContent();
        splitTime = splitTime.replaceAll("\\s+","");
        String[] splitTimeStringData = splitTime.split(":");
        int[] splitTimeData = new int[splitTimeStringData.length];
        // seconds, minutes, hours
        int[] calculations = {1000,60000,3600000};
        int result = 0;
        int index = splitTimeStringData.length-1;
        for(int i = 0; i < splitTimeStringData.length; i++)
        {
            splitTimeData[i] = Integer.parseInt(splitTimeStringData[i]);
            result += calculations[index--]*splitTimeData[i];
        }
        parsedRun.addSegment(new Segment(segmentName,result));
    }

    private Element[] getElementsByTagName(Element e, String elementName) {
        NodeList nodes = e.getElementsByTagName(elementName);
        int nodeCount = nodes.getLength();

        if (nodeCount != 0) {
            Element[] elements = new Element[nodeCount];

            for (int i = 0; i < nodeCount; i++) {
                Element element = (Element) nodes.item(i);
                elements[i] = element;
            }
            return elements;
        } else {
            return empty;
        }
    }

}
