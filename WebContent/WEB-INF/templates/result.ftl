<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-grid.css">
</head>
    <body>
    submitted
    
<#if f==0>
<#else>
	<ul class="unordered-list">
   			   <li>${firsts}</li>
	 </ul>
</#if>

<#if s==0>
<#else>
	<ul class="list-group">
   			   <li class="list-group-item">${second}</li>
	 </ul>
</#if>

<#if t==0>
<#else>
<table class="table" style "width:100%" name="table3">
<#assign rd = rowInputs>
<#assign md = thirds>
<#assign cd = colNums>
<#assign fhcd = cd>
<#assign tempd = 0>
<#list 1..rd as frd>
<tr>
	<#assign y = 1>
	<#list md as fhd>
			<td>${fhd}></td>
			<#assign y = y+1>
			<#assign tempd = tempd + 1>
			<#if y==fhcd>
				<#break>
			</#if>
	</#list>
 	
 </tr>
 </#list>      
</table> 
</#if>

<#if fo==0>
<#else>   
<ul class="list-group">
        <#assign m = fours>
        <#list m as four>
      <li class="list-group-item">${four}</li>
        </#list>
 </ul>
 </#if>
 
<#if fi==0>
<#else>
 <ul class="list-group"">
        <#assign m = fives>
        <#list m as five>
      <li class="list-group-item">${five}</li>
        </#list>
 </ul>
</#if>

<#if i==0>
<#else>
<table class="table" style "width:100%" name="table3">
<#assign r = rowsInputs>
<#assign m = inputFHS>
<#assign f = inputFS>
<#assign c = colsNums>
<tr>
     	   <#list m as inputFH>
     	  <th>${inputFH}</th>
     	   </#list>
</tr>
<#assign fhc = c>
<#assign lm = 0>
<#assign temp = 0>
<#list 1..r as fr>
<tr>
	<#assign y = 1>
	<#list f as fh>
			<td>${fh}></td>
			<#assign y = y+1>
			<#assign temp = temp + 1>
			<#if y==fhc>
				<#break>
			</#if>
	</#list>
 	
 </tr>
 </#list>
</table> 
 </#if>
        
</body>
</html>