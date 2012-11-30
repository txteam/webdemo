<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>行事历</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {
    $("#eventscalendar").wijevcal({
        culture: "zh"
    });
});
</script>
</head>
<body>
<div id="eventscalendar"></div>
</body>
</html>