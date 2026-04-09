<!DOCTYPE html>
<html>
<head><title>View User Schedule</title></head>
<body>
<h1>CourseFriends Schedules</h1>
<p>Select a user to view their schedule:</p>
<form method="get" action="/user-schedule">
    <select name="username" required>
        <option value="">Choose User</option>
        <#list users as user>
            <option value="${user}">${user}</option>
        </#list>
    </select>
    <input type="submit" value="View Schedule">
</form>
</body>
</html>