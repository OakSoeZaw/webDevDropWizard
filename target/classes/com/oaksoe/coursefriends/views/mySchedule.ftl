<!DOCTYPE html>
<html>
<head><title>My Schedule</title></head>
<body>
<h1>My Schedule (${username})</h1>
<p>Your classes:</p>

<#if myClasses?has_content>
    <ul>
        <#list myClasses as cls>
            <li>
                ${cls.code} - ${cls.title}
                <form method="post" action="/my-schedule" style="display:inline">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="courseCode" value="${cls.code}">
                    <input type="submit" value="Remove">
                </form>
            </li>
        </#list>
    </ul>
<#else>
    <p>No classes added yet.</p>
</#if>

<h3>Add a Class</h3>
<form method="post" action="/my-schedule">
    <input type="hidden" name="action" value="add">
    <select name="courseCode">
        <#list allClasses as cls>
            <option value="${cls.code}">${cls.code} - ${cls.title}</option>
        </#list>
    </select>
    <input type="submit" value="Add Class">
</form>

<br>
<a href="/user-schedule">View your Friends Schedules</a>
</body>
</html>