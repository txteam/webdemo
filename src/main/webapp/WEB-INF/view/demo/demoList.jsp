<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add demo</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {
	$("#demo").wijgrid({ 
        allowSorting: true, 
        allowPaging: true, 
        pageSize: 10, 
        data: [ 
            [27, 'Canada', 'Adams, Craig', 'RW', 'R', 32, 2009, 'Seria, Brunei'], 
            [43, 'Canada', 'Boucher, Philippe', 'D', 'R', 36, 2008, 'Saint-Apollinaire, Quebec'], 
            [24, 'Canada', 'Cooke, Matt', 'LW', 'L', 30, 2008, 'Belleville, Ontario'], 
            [87, 'Canada', 'Crosby, Sidney (C)', 'C', 'L', 21, 2005, 'Cole Harbour, Nova Scotia'], 
            [1, 'United States', 'Curry, John', 'G', 'L', 25, 2007, 'Shorewood, Minnesota'], 
            [9, 'Canada', 'Dupuis, Pascal', 'W', 'L', 30, 2008, 'Laval, Quebec'], 
            [7, 'United States', 'Eaton, Mark', 'D', 'L', 32, 2006, 'Wilmington, Delaware'], 
            [26, 'Ukraine', 'Fedotenko, Ruslan', 'LW', 'L', 30, 2008, 'Kiev, U.S.S.R.'], 
            [29, 'Canada', 'Fleury, Marc-Andre', 'G', 'L', 24, 2003, 'Sorel, Quebec'], 
            [32, 'Canada', 'Garon, Mathieu', 'G', 'R', 31, 2009, 'Chandler, Quebec'], 
            [2, 'United States', 'Gill, Hal', 'D', 'L', 34, 2008, 'Concord, Massachusetts'], 
            [28, 'Canada', 'Godard, Eric', 'RW', 'R', 29, 2008, 'Vernon, British Columbia'], 
            [3, 'United States', 'Goligoski, Alex', 'D', 'L', 23, 2004, 'Grand Rapids, Minnesota'], 
            [55, 'Russia', 'Gonchar, Sergei (A)', 'D', 'L', 35, 2005, 'Chelyabinsk, U.S.S.R.'], 
            [13, 'United States', 'Guerin, Bill', 'RW', 'R', 38, 2009, 'Worcester, Massachusetts'], 
            [42, 'Canada', 'Jeffrey, Dustin', 'C', 'L', 21, 2007, 'Sarnia, Ontario'], 
            [48, 'Canada', 'Kennedy, Tyler', 'C', 'R', 22, 2004, 'Sault Ste.Marie, Ontario'], 
            [14, 'Canada', 'Kunitz, Chris', 'LW', 'L', 29, 2009, 'Regina, Saskatchewan'], 
            [58, 'Canada', 'Letang, Kristopher', 'D', 'R', 22, 2005, 'Montreal, Quebec'], 
            [65, 'United States', 'Lovejoy, Ben', 'D', 'R', 25, 2008, 'Canaan, New Hampshire'], 
            [71, 'Russia', 'Malkin, Evgeni (A)', 'C', 'L', 22, 2004, 'Magnitogorsk, U.S.S.R.'], 
            [14, 'Canada', 'Minard, Chris', 'C', 'L', 27, 2007, 'Owen Sound, Ontario'], 
            [44, 'United States', 'Orpik, Brooks', 'D', 'L', 28, 2001, 'San Francisco, California'], 
            [81, 'Slovakia', 'Satan, Miroslav', 'RW', 'L', 34, 2008, 'Jacovce, Czechoslovakia'], 
            [4, 'United States', 'Scuderi, Rob', 'D', 'L', 30, 1998, 'Syosset, New York'], 
            [11, 'Canada', 'Staal, Jordan', 'C', 'L', 20, 2006, 'Thunder Bay, Ontario'], 
            [17, 'Czech Republic', 'Sykora, Petr', 'RW', 'L', 32, 2007, 'Plzen�, Czechoslovakia'], 
            [22, 'United States', 'Taffe, Jeff', 'LW', 'L', 28, 2007, 'Hastings, Minnesota'], 
            [25, 'Canada', 'Talbot, Maxime', 'C', 'L', 25, 2002, 'LeMoyne, Quebec'], 
            [15, 'Canada', 'Zigomanis, Michael', 'C', 'R', 28, 2008, 'Toronto, Ontario'] 
        ], 
        columns: [ 
            { headerText: "Number" }, { headerText: "Nationality" }, { headerText: "Player" }, { headerText: "Position" }, 
            { headerText: "Handedness" }, { headerText: "Age" }, { headerText: "Acquired" }, { headerText: "Birthplace" } 
        ] 
    }); 
	$("#testDemo").wijgrid({ 
        allowSorting: true, 
        allowPaging: true
    }); 
});
</script>
</head>
<body>
<div class="header">常用菜单>demo列表</div>
<br/>
<table>
	<tr>
		<th>查询条件一</th>
		<td><input id="" value="" type="text"/></td>
		<th>查询条件二</th>
		<td><input id="" value="" type="text"/></td>
	</tr>
</table>
<br/>

<table id="demo"> 
</table>

<div class="header">demo列表</div>
<table id="testDemo">
  <thead>
    <th>column0</th><th>columnN</th>
  </thead>
  <tbody>
    <tr>
      <td>cell00</td><td>cell0N</td>
    </tr>
    <tr>
      <td>cellN0</td><td>cellNN</td>
    </tr>
  </tbody>
</table>
</body>
</html>