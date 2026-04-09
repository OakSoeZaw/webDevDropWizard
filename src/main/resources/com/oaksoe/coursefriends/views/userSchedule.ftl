<!DOCTYPE html>
<html>
<head><title>View User Schedule</title></head>
<body>
<h1>CourseFriends Schedules</h1>
<h2>${username}'s Schedule</h2>

<#if classes?has_content>
    <ul>
        <#list classes as cls>
            <li>${cls.code} - ${cls.title}</li>
        </#list>
    </ul>
<#else>
    <p>No classes added for this user.</p>
</#if>

<br>
<a href="/user-schedule">Back to user list</a>
</body>
</html>