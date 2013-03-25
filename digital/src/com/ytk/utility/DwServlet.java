/*package org.apache.dwexample.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
*//**
 * Copyright 2005 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *//*

*//**
 * The DWServlet is responsible for taking in commands from the sample developerWorks application and converting them to
 * well-formed Solr commands, which can be viewed and then submitted to Solr for processing.
 * <p/>
 * This code is for demonstration purposes only and is not intended for production use.
 *//*
public class DWServlet extends HttpServlet
{
    private static final String TYPE = "type";
    private static final String URL = "url";
    private static final String PUBLISHED = "published";
    private static final String CONTENT = "content";
    private static final String RATING = "rating";
    private static final String CREATION_DATE = "creationDate";
    private static final String KEYWORDS = "keywords";
    private static final String TITLE = "title";
    public static final String LINE_SEP = System.getProperty("line.separator");
    private static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    private static SimpleDateFormat solrFormatter =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);
    public static TimeZone UTC = TimeZone.getTimeZone("UTC");
    private int portNumber = 8080;
    private static final String SHOW_INDEX_XML_COMMAND_JSP = "/showIndexXMLCommand.jsp";
    private static final String SHOW_SOLR_RESULTS_JSP = "/showSolrResults.jsp";
    private String solrLocation;
    private static final String SHOW_QUERY_COMMAND_JSP = "/showQueryCommand.jsp";
    private static final String FACET_RESULTS_JSP = "facetResults.jsp";
    private DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

    *//**
     * Process the incoming requests, based on the "type" passed in as a hidden field on most pages.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     *//*
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String type = request.getParameter(TYPE);
        if ("add".equals(type))
        {
            //Create the Solr Document.  Real apps should probably use a DocumentBuilder
            processAdd(request, response);
        }
        else if ("commit".equals(type))
        {
            processCommit(request, response);
        }
        else if ("optimize".equals(type))
        {
            processOptimizeCommand(request, response);
        }
        else if ("search".equals(type))
        {
            processSearch(request, response);
        }
        else if ("delete".equals(type))
        {
            processDelete(request, response);
        }
        else if ("submitCommand".equals(type))
        {
            processSubmitCommand(request, response);
        }
        else if ("submitQuery".equals(type))
        {
            processSubmitQuery(request, response);
        }
        else if ("facet".equals(type))
        {
            try
            {
                processFacetRequest(request, response);
            }
            catch (ParserConfigurationException e)
            {
                throw new ServletException(e);
            }
            catch (SAXException e)
            {
                throw new ServletException(e);
            }
        }
        else
        {
            throw new ServletException("Invalid Request Type: " + type);
        }
    }

    //Indexing Related commands

    *//**
     * Create an add command, made up of fields in a document.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     *//*
    private void processAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        StringBuilder command = new StringBuilder();
        //Probably should use a DOM based construction, but this is simpler for now
        String url = request.getParameter(URL);
        command.append("<add>").append(LINE_SEP).append("<doc>")
                .append("<field name=\"url\">").append(url).append("</field>").append(LINE_SEP);

        String title = request.getParameter(TITLE);
        if (title != null && title.equals("") == false)
        {
            command.append("<field name=\"title\">").append(title).append("</field>").append(LINE_SEP);
        }
        String keywordsStr = request.getParameter(KEYWORDS);
        if (keywordsStr != null && keywordsStr.equals("") == false)
        {
            command.append("<field name=\"keywords\">").append(keywordsStr).append("</field>").append(LINE_SEP);
        }

        String dateStr = request.getParameter(CREATION_DATE);
        if (dateStr != null && dateStr.equals("") == false)
        {
            Date date;
            try
            {
                date = formatter.parse(dateStr);
            }
            catch (ParseException e)
            {
                throw new ServletException(e);
            }
            //The Solr DateField wants dates formatted in a particular way.
            //convert our date format to Solr DateField format: yyyy-MM-dd'T'HH:mm:ss.SSSZ
            dateStr = solrFormatter.format(date);
            //dateStr = dateStr.replace("T", "'T'");
            command.append("<field name=\"creationDate\">").append(dateStr).append("Z").append("</field>").append(LINE_SEP);
        }
        String ratingStr = request.getParameter(RATING);
        if (ratingStr != null && ratingStr.equals("") == false)
        {
            command.append("<field name=\"rating\">").append(ratingStr).append("</field>").append(LINE_SEP);
        }
        String content = request.getParameter(CONTENT);
        if (content != null && content.equals("") == false)
        {
            command.append("<field name=\"content\">").append(content).append("</field>").append(LINE_SEP);
        }
        String publishedStr = request.getParameter(PUBLISHED);

        if (publishedStr != null && publishedStr.equals("") == false)
        {
            command.append("<field name=\"published\">").append(publishedStr).append("</field>").append(LINE_SEP);
        }
        command.append("</doc>").append(LINE_SEP);
        command.append("</add>").append(LINE_SEP);

        processIndexCommand(request, response, command.toString());
    }

    *//**
     * Create a commit command
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     *//*
    private void processCommit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        String command = "<commit/>";
        processIndexCommand(request, response, command);
    }

    *//**
     * Create an optimize command to send to Solr and display it on the SHOW_XMLCOMMAND_JSP page
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     *//*
    private void processOptimizeCommand(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String command = "<optimize/>";

        processIndexCommand(request, response, command);
    }

    *//**
     * Delete by ID.  This app does not support delete by query at this point, even though Solr does.
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     *//*
    private void processDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String urlToDelete = request.getParameter("deleteURL");
        String command = "<delete>" + LINE_SEP +
                "<id>" + urlToDelete + LINE_SEP
                + "</id></delete>";

        processIndexCommand(request, response, command);

    }

    //SEARCH Related Commands
    *//**
     * Build a Search request for Solr out of the fields entered by the user.
     *<p/>
     * See http://wiki.apache.org/solr/CoreQueryParameters, http://wiki.apache.org/solr/CommonQueryParameters, http://wiki.apache.org/solr/HighlightingParameters
     * and http://wiki.apache.org/solr/SolrRequestHandler
     *  
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     *//*
    private void processSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        StringBuilder query = new StringBuilder();
        String url = request.getParameter(URL);
        if (url != null && url.equals("") == false)
        {
            //Escape the colon in the URL, as it is a reserved token in Lucene
            url = url.replace(":", "\\:");
            query.append(URL).append(":").append(URLEncoder.encode(url, "UTF-8"));
        }
        String title = request.getParameter(TITLE);
        if (title != null && title.equals("") == false)
        {
            addSpaceToQuery(query);
            query.append(TITLE).append(":").append(URLEncoder.encode(title, "UTF-8"));
        }
        String keywords = request.getParameter(KEYWORDS);
        if (keywords != null && keywords.equals("") == false)
        {
            addSpaceToQuery(query);
            query.append(KEYWORDS).append(":").append(URLEncoder.encode(keywords, "UTF-8"));
        }
        String dateStr = request.getParameter(CREATION_DATE);
        if (dateStr != null && dateStr.equals("") == false)
        {
            Date date;
            try
            {
                date = formatter.parse(dateStr);
            }
            catch (ParseException e)
            {
                throw new ServletException(e);
            }
            //convert our date format to Solr DateField format: yyyy-MM-dd'T'HH:mm:ss.SSSZ
            dateStr = solrFormatter.format(date);
            //dateStr = dateStr.replace("T", "'T'");
            addSpaceToQuery(query);
            query.append(CREATION_DATE).append(":").append(URLEncoder.encode(dateStr, "UTF-8"));
        }
        String ratingStr = request.getParameter(RATING);
        if (ratingStr != null && ratingStr.equals("") == false)
        {
            addSpaceToQuery(query);
            query.append(RATING).append(":").append(URLEncoder.encode(ratingStr, "UTF-8"));
        }
        String content = request.getParameter(CONTENT);
        if (content != null && content.equals("") == false)
        {
            addSpaceToQuery(query);
            query.append(CONTENT).append(":").append(URLEncoder.encode(content, "UTF-8"));
        }
        String published = request.getParameter(PUBLISHED);
        if (published != null && published.equals("") == false)
        {
            addSpaceToQuery(query);
            query.append(PUBLISHED).append(":").append(published);
        }

        //Handle any sorting as part of the "q" parameter
        String sort = request.getParameter("sort");
        //only adding sort if it is not score, since score is the default
        if (sort != null && sort.equals("") == false && sort.equals("score") == false)
        {
            query.append("; ").append(sort);
            //only add direction if we are sorting
            String direction = request.getParameter("direction");
            if (direction != null && direction.equals("") == false)
            {
                query.append(" ").append(direction);
            }
            else
            {
                query.append(" desc");
            }
        }

        //Set the default operator to use for boolean queries
        String operator = request.getParameter("operator");
        if (operator != null && operator.equals("AND"))
        {
            query.append("&q.op=").append("AND");
        }
        else
        {
            //DEFAULT IS OR, DO NOTHING
        }
        String start = request.getParameter("start");
        if (start != null && start.equals("") == false)
        {
            query.append("&start=").append(start);
        }
        String numResults = request.getParameter("numResults");
        if (numResults != null && numResults.equals("") == false)
        {
            query.append("&rows=").append(numResults);
        }
        String highlight = request.getParameter("highlight");
        if (highlight != null && highlight.equals("") == false)
        {
            query.append("&hl=true");
        }
        //Add this on to the beginning now so that addSpaceToQuery works properly
        query.insert(0, "fl=*,score&q=");
        processQueryCommand(request, response, query.toString(), SHOW_QUERY_COMMAND_JSP);
    }

    *//**
     * Create a Facet Query request.  A facet request is similar to a regular search query, plus it has parameters related to where
     * to get the facets from, etc.
     * <p/>
     * See http://wiki.apache.org/solr/SolrFacetingOverview and http://wiki.apache.org/solr/SimpleFacetParameters
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     *//*
    private void processFacetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParserConfigurationException, SAXException
    {
        String all = request.getParameter("all");
        if (all != null && all.equals("") == false)
        {
            StringBuilder finalQuery = new StringBuilder("fl=*,score&facet=true&q=");
            String facetField = request.getParameter("facet.field");
            finalQuery.append(URLEncoder.encode(all, "UTF-8"));
            if (facetField != null && facetField.equals("") == false)
            {
                finalQuery.append("&facet.field=").append(URLEncoder.encode(facetField, "UTF-8"));
            }
            else
            {
                //default is keywords
                finalQuery.append("&facet.field=keywords");
            }
            String facetQuery = request.getParameter("facet.query");
            if (facetQuery != null && facetQuery.equals("") == false)
            {
                finalQuery.append("&facet.query=").append(URLEncoder.encode(facetQuery, "UTF-8"));
            }
            request.setAttribute("all", all);
            request.setAttribute("facet.field", facetField);
            request.setAttribute("facet.query", facetQuery);
            String getResults = sendGetCommand(finalQuery.toString(), solrLocation + "/solr/select/");
            //Build a Dom to manipulate for convenience and put it in the request.  Real applications would want to use
            //something faster, like SAX
            DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(new InputSource(new StringReader(getResults)));
            if (doc != null)
            {
                request.setAttribute("resultsDoc", doc);
                if (doc != null)
                {
                    List<SearchResult> searchResults = convertDoc(doc);
                    request.setAttribute("searchResults", searchResults);
                    Map<String, Integer> facetCounts = getFacetCounts(doc);
                    request.setAttribute("facetCounts", facetCounts);
                }
            }
            //processQueryCommand(request, response, finalQuery.toString(), FACET_RESULTS_JSP);
            request.getRequestDispatcher(FACET_RESULTS_JSP).forward(request, response);

        }
        else
        {
            throw new ServletException("Must specify a query");
        }
    }

    *//**
     * Extract the Facet counts from the results Document.
     * @param doc The XML Document
     * @return
     *//*
    private Map<String, Integer> getFacetCounts(Document doc)
    {
        Map<String, Integer> result = new HashMap<String, Integer>();
        NodeList facetList = doc.getElementsByTagName("lst");
        if (facetList != null && facetList.getLength() > 0)
        {
            for (int i = 0; i < facetList.getLength(); i++)
            {
                Element elt = (Element) facetList.item(i);
                String name = elt.getAttribute("name");
                if (name != null && name.equals("facet_fields"))
                {
                    NodeList counts = elt.getElementsByTagName("int");
                    for (int j = 0; j < counts.getLength(); j++)
                    {
                        Element count = (Element) counts.item(j);
                        String countName = count.getAttribute("name");
                        result.put(countName, new Integer(count.getTextContent()));
                    }
                }
            }
        }
        return result;
    }

    *//**
     * Get a list of {@link SearchResult}s from the XML Document.
     * @param doc
     * @return
     *//*
    private List<SearchResult> convertDoc(Document doc)
    {
        //Get the results out of the XML
        NodeList nodes = doc.getElementsByTagName("doc");
        List<SearchResult> result = Collections.emptyList();
        if (nodes != null)
        {
            result = new ArrayList<SearchResult>(nodes.getLength());
            for (int i = 0; i < nodes.getLength(); i++)
            {
                Node node = nodes.item(i);
                NodeList children = node.getChildNodes();
                SearchResult sr = new SearchResult();
                result.add(sr);
                for (int j = 0; j < children.getLength(); j++)
                {
                    Element elt = (Element) children.item(j);
                    //The name attribute tells us what property on the Search Result.  Again, SAX makes more sense, but this is easier for now
                    String attrib = elt.getAttribute("name");
                    String text = elt.getTextContent();
                    if (attrib.equals("content"))
                    {
                        sr.setContent(text);
                    }
                    else if (attrib.equals("creationDate"))
                    {
                        sr.setDate(text);
                    }
                    else if (attrib.equals("score"))
                    {
                        sr.setScore(text);
                    }
                    else if (attrib.equals("keywords"))
                    {
                        sr.setKeywords(text);
                    }
                    else if (attrib.equals("published"))
                    {
                        sr.setPublished(text);
                    }
                    else if (attrib.equals("rating"))
                    {
                        sr.setRating(text);
                    }
                    else if (attrib.equals("title"))
                    {
                        sr.setTitle(text);
                    }
                    else if (attrib.equals("url"))
                    {
                        sr.setUrl(text);
                    }
                }
            }
        }
        return result;
    }


    //Helper methods
    *//**
     * Either forward the request on to Solr, or send it to be displayed back to the user
     * @param request
     * @param response
     * @param command
     * @throws ServletException
     * @throws IOException
     *//*
    private void processIndexCommand(HttpServletRequest request, HttpServletResponse response, String command)
            throws ServletException, IOException
    {
        System.out.println("Command: " + command);
        if (isShowCommand(request) == true)
        {
            setCommand(request, command);
            request.getRequestDispatcher(SHOW_INDEX_XML_COMMAND_JSP).forward(request, response);
        }
        else
        {
            processSubmitCommand(request, response, command);
        }
    }

    *//**
     * Either forward the query on to Solr, or send it to the destination URL
     * @param request
     * @param response
     * @param query
     * @param destination
     * @throws ServletException
     * @throws IOException
     *//*
    private void processQueryCommand(HttpServletRequest request, HttpServletResponse response, String query, String destination)
            throws ServletException, IOException
    {
        System.out.println("Query: " + query);
        request.setAttribute("query", query);
        if (isShowCommand(request) == true)
        {
            request.getRequestDispatcher(destination).forward(request, response);
        }
        else
        {
            processSubmitQuery(request, response);
        }
    }

    *//**
     * Sumbit a Post Command, and then forward to {@link #SHOW_SOLR_RESULTS_JSP}
     * @param request
     * @param response
     * @param command
     * @throws IOException
     * @throws ServletException
     *//*
    private void processSubmitCommand(HttpServletRequest request, HttpServletResponse response, String command)
            throws IOException, ServletException
    {
        String results = sendPostCommand(command, solrLocation + "/solr/update");
        //Send the command over HTTP to be handled by Solr
        request.setAttribute("results", results);
        request.getRequestDispatcher(SHOW_SOLR_RESULTS_JSP).forward(request, response);
    }

    *//**
     * Get the command from the request and send it Solr via {@link #sendPostCommand(String,String)}, then put the results into the request
     * and forward on to the results page.
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     *//*
    private void processSubmitCommand(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        String command = request.getParameter("command");

        processSubmitCommand(request, response, command);
    }

    *//**
     * Submit a {@link #sendGetCommand(String,String)} and then forward to {@link #SHOW_SOLR_RESULTS_JSP}
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     *//*
    private void processSubmitQuery(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String query = request.getParameter("query");
        if (query == null || query.equals("") == true)
        {
            //We may be getting the query from the request, not the form
            query = (String) request.getAttribute("query");
        }
        String results = sendGetCommand(query, solrLocation + "/solr/select/");
        //Send the command over HTTP to be handled by Solr
        request.setAttribute("results", results);
        request.getRequestDispatcher(SHOW_SOLR_RESULTS_JSP).forward(request, response);
    }

    *//**
     * Put the XML Command into the request so that it can be displayed
     *
     * @param request
     * @param command
     *//*
    private void setCommand(HttpServletRequest request, String command)
    {
        request.setAttribute("command", command);
    }

    *//**
     * Send the command to Solr using a Post
     *
     * @param command
     * @param url
     * @return
     * @throws IOException
     *//*
    private String sendPostCommand(String command, String url)
            throws IOException
    {
        String results = null;
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);

        RequestEntity re = new StringRequestEntity(command, "text/xml", "UTF-8");
        post.setRequestEntity(re);
        try
        {
            // Execute the method.
            int statusCode = client.executeMethod(post);

            if (statusCode != HttpStatus.SC_OK)
            {
                System.err.println("Method failed: " + post.getStatusLine());
            }

            // Read the response body.
            byte[] responseBody = post.getResponseBody();

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            results = new String(responseBody);
        }
        catch (HttpException e)
        {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            // Release the connection.
            post.releaseConnection();
        }
        return results;
    }

    *//**
     * Send the command to Solr using a GET
     * @param queryString
     * @param url
     * @return
     * @throws IOException
     *//*
    private String sendGetCommand(String queryString, String url)
            throws IOException
    {
        String results = null;
        HttpClient client = new HttpClient();
        GetMethod get = new GetMethod(url);
        get.setQueryString(queryString.trim());

        client.executeMethod(get);
        try
        {
            // Execute the method.
            int statusCode = client.executeMethod(get);

            if (statusCode != HttpStatus.SC_OK)
            {
                System.err.println("Method failed: " + get.getStatusLine());
            }

            // Read the response body.
            byte[] responseBody = get.getResponseBody();

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            results = new String(responseBody);
        }
        catch (HttpException e)
        {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            // Release the connection.
            get.releaseConnection();
        }
        return results;
    }

    *//**
     * Only add space to the query if there is already something in the query.
     * @param query
     *//*
    private void addSpaceToQuery(StringBuilder query)
    {
        if (query.length() > 0)
        {
            //Use a + sign for URL Encoding
            query.append('+');
        }
    }

    private boolean isShowCommand(HttpServletRequest request)
    {
        boolean result = false;
        String tmp = request.getParameter("showCommand");
        result = Boolean.parseBoolean(tmp);

        return result;
    }


    *//**
     * Call {@link #doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
     *//*
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException
    {
        doGet(httpServletRequest, httpServletResponse);
    }


    *//**
     * Get the port number from the web.xml.
     * @param servletConfig
     * @throws ServletException
     *//*
    public void init(ServletConfig servletConfig) throws ServletException
    {
        String port = servletConfig.getInitParameter("port");
        if (port != null && port.equals("") == false)
        {
            portNumber = Integer.parseInt(port);
        }
        solrLocation = "http://localhost:" + portNumber;
        solrFormatter.setTimeZone(UTC);
        System.out.println("Solr must be running at http://localhost:" + portNumber + "/solr for this demo to work");
    }
}
*/