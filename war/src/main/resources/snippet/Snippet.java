package snippet;

public class Snippet {
	<ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
		xmlns:g="urn:import:com.google.gwt.user.client.ui" xmlns:p1="urn:import:com.google.gwt.user.cellview.client"
		xmlns:d='urn:import:com.google.gwt.user.datepicker.client'>
	
		<ui:image resource="input_text.jpg" field="input_text"
			repeatStyle="Horizontal"></ui:image>
		<ui:image resource="preview_bg.jpg" field="preview_bg"
			repeatStyle="Horizontal"></ui:image>
		<ui:image field="post_bg" resource="post_bg.jpg" repeatStyle="Horizontal"></ui:image>
		<ui:style src="new2.css" field="new2"></ui:style>
	
		<g:HTMLPanel styleName="{new2.frame-wrap}">
}

