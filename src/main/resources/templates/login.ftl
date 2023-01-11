<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Login page
<@l.login "/login" />
<a href="/registration">Add new user</a>
<a href="/upload">upload file</a>
</@c.page>