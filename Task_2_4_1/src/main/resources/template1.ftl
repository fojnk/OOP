<!DOCTYPE html>
<html lang="en">
<head>
    <title>Results</title>
</head>
<body>
    <#list tasks as task>
        <h1>${task.name}</h1>
        <table border = 1, style="width:50%">
            <tr>
                <th>Name</th>
                <th>Build</th>
                <th>JavaDoc</th>
                <th>Checkstyle</th>
                <th>Tests</th>
                <th>Mark</th>
            </tr>
            <#list tasksResults as student, tasks>
                <#list tasks as task1, result>
                    <#if task1.name == task.name>
                    <tr>
                        <th>${student.name}</th>
                        <th><#if result.build>
                           <span class="green"><b>+</b></span>
                        <#else>
                            <span class="red"><b>-</b></span>
                        </#if>
                        </th>
                        <th><#if result.javadoc>
                           <span class="green"><b>+</b></span>
                        <#else>
                            <span class="red"><b>-</b></span>
                        </#if>
                        </th>
                        <th><#if result.checkstyle>
                           <span class="green"><b>+</b></span>
                        <#else>
                            <span class="red"><b>-</b></span>
                        </#if>
                        </th>
                        <th>${result.passedTests}/${result.amountOfTests}</th>
                        <th>${result.mark}</th>
                    </tr>
                    <#else>
                    </#if>
                </#list>
            </#list>
        </table> 
    </#list>
</body>