<!DOCTYPE html>
<html lang="en">
<head>
    <title>Results</title>
    <style>
        .styled-table {
            border-collapse: collapse;
            margin: 25px 0;
            font-size: 0.9em;
            font-family: sans-serif;
            min-width: 400px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
        }

        .styled-table thead tr {
            background-color: #009879;
            color: #ffffff;
            text-align: left;
        }

        .styled-table th,
        .styled-table td {
            padding: 12px 15px;
        }

        .styled-table tbody tr {
            border-bottom: 1px solid #dddddd;
        }

        .styled-table tbody tr:nth-of-type(even) {
            background-color: #f3f3f3;
        }

        .styled-table tbody tr:last-of-type {
            border-bottom: 2px solid #009879;
        }

        .styled-table tbody tr.active-row {
            font-weight: bold;
            color: #009879;
        }
    </style>
</head>
<body>
    <#list tasks as task>
        <table border = 1, class="styled-table">
            <tr>
                <th>${task.name}</th>
            </tr>
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
                        <th>${result.passedTests}/${result.skippedTests}/${result.amountOfTests}</th>
                        <th>${result.mark}</th>
                    </tr>
                    <#else>
                    </#if>
                </#list>
            </#list>
        </table> 
    </#list>
</body>