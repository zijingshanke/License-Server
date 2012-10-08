package com.chinarewards.gwt.license.util;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class XmlUtil_GWT {

	public static void main(String[] args) {
//		Document doc = parseXml("<root><node>333</node></root>");
//		Document doc = parseXml("<?xml version=\"1.0\" encoding=\"GB2312\" ?><root><result>SUCCESS</result><info>201202031828463440.jpg</info></root>");
//		
//		System.out.println(getSingleNodeText(doc, "result"));
		String str="<root><result>SUCCESS</result><info>201202031828463440.jpg</info>";
//		System.out.println(str.substring(str.indexOf("<result>"), str.indexOf("</result>")));
		System.out.println(getNormalNodeText(str, "<result>","</result>"));
		System.out.println(getNormalNodeText(str, "<info>","</info>"));
		
	}
	
	public static String getNormalNodeText(String str,String beginStr,String endStr){
		str=str.substring(str.indexOf(beginStr)+beginStr.length(), str.indexOf(endStr));
		return str;
	}

	public static String getSingleNodeText(Document doc, String tagName) {
		String itemValue = "";
		if (doc != null) {

			NodeList nodeList = doc.getElementsByTagName(tagName);

			for (int i = 0; i < nodeList.getLength(); i++) {
				// System.out.println("node item:" + nodeList.item(i));
				// System.out.println(nodeList.item(i).getFirstChild()
				// .getNodeValue());
				// System.out.println(nodeList.item(i).toString());
				itemValue = nodeList.item(i).getFirstChild().getNodeValue();
				return itemValue;
			}
		} else {
			System.out.println("document is null...");
		}

		return itemValue;
	}

	public static Document parseXml(String contents) {
		Document doc = null;
		try {
			doc = XMLParser.parse(contents);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return doc;
	}

	/**
	 * response.setContentType("text/plain");
	 * 
	 * */
	public static String replaceSpecialStr(String content) {
		if (content != null) {
			// 以text/plain格式返回需要
			content = content.replace("<pre>", "");
			content = content.replace("</pre>", "");
			content = content.replace("&lt;", "<");
			content = content.replace("&gt;", ">");
			// chrome
			String chromeStr = "<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">";
			content = content.replace(chromeStr, "");
			
			//text/plain IE8
			content = content.replace("<DIV>", "");
			content = content.replace("<DIV class=e>", "");
			content = content.replace("<SPAN class=b>", "");
			content = content.replace("<SPAN class=m>", "");
			content = content.replace("<SPAN class=t>", "");
			content = content.replace("<SPAN class=tx>", "");
			content = content.replace("<SPAN class=pi>", "");
			content = content.replace("</SPAN>", "");
			content = content.replace("</DIV>", "");
			content = content.replace("&nbsp;", "");
			content = content.replace("<DIV style=\"TEXT-INDENT: -2em; MARGIN-LEFT: 1em\">", "");
			content = content.replace("<DIV style=\"TEXT-INDENT: -2em; MARGIN-LEFT: 1em\" class=c>", "");
			content = content.replace("<A class=b onfocus=h() onclick=\"return false\" href=\"#\">-</A>", "");	
			content=content.replace("\n", "");
			content=content.replace("\r", "");
			content=content.replace("\r\n", "");
			content=content.replace("\n\r", "");			
			content=content.replace("\t", "");
			content=content.replace("GB2312\" ?", "GB2312\"?");
		}

		return content;
	}
}
