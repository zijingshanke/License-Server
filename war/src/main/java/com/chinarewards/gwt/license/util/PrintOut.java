package com.chinarewards.gwt.license.util;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;

@SuppressWarnings("deprecation")
public class PrintOut {

	public static native void it() /*-{
		$wnd.print();
	}-*/;

	public static native void buildFrame(String html) /*-{
		var frame = $doc.getElementById('__printingFrame');
		if (!frame) {
			$wnd.alert("Error: Can't find printing frame.");
			return;
		}
		var doc = frame.contentWindow.document;
		doc.open();
		doc.write(html);
		doc.close();
		frame.height = frame.contentWindow.document.body.scrollHeight + 20
				+ "px";
	}-*/;

	public static native void printFrame() /*-{
		var frame = $doc.getElementById('__printingFrame');
		frame = frame.contentWindow;
		frame.focus();
		frame.print();
	}-*/;

	public static class PrintFrame implements Command {
		public void execute() {
			printFrame();
		}
	}

	public static PrintFrame printFrameCommmand = new PrintFrame();


	public static void it(String html) {
		try {
			buildFrame(html);
			DeferredCommand.addCommand(printFrameCommmand); // pring out
		} catch (Throwable exc) {
			Window.alert(exc.getMessage());
		}
	}

	public static void it(UIObject obj) {
		it("", obj.getElement().toString());
	}

	public static void it(Element element) {
		it("", element.toString());
	}

	public static void it(String style, String it) {
		it("" + style + "\n" + it + "");
	}

	public static void it(String style, UIObject obj) {
		it(style, obj.getElement().toString());
	}

	public static void it(String style, Element element) {
		it(style, element.toString());
	}
}