<%--
  Created by IntelliJ IDEA.
  User: amankaushik
  Date: 18/2/19
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>view-details</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<h2> <%="Quiz"%></h2>
<table>
    <tr>
        <th>Questions</th>
        <th>Options</th>
        <th>Score</th>
    </tr>
    <tr>
        <td>Static Data</td>
        <td>Static Data</td>
        <td>Static Data</td>
    </tr>
    <tr>
        <td><span>Question #1<span></td>
        <td>
            <ol type = 'a'>
                <li>First</li>
                <li>Second</li>
                <li>Third</li>
                <li>Fourth</li>
            </ol>
        </td>
        <td>10</td>
    </tr>
    <tr>
        <td><span>Question #2 </span></td>
        <td>
            <ol type = 'a'>
                <li>First</li>
                <li>Second</li>
                <li>Third</li>
                <li>Fourth</li>
            </ol>
        </td>
        <td>5</td>
    </tr>
    <tr>
        <td><span > Question #3 </span></td>
        <td>
            <ol type = 'a'>
                <li>First</li>
                <li>Second</li>
                <li>Third</li>
                <li>Fourth</li>
            </ol>
        </td>
        <td>15</td>
    </tr>
    <tr>
        <td><span > Question #4 </span></td>
        <td>
            <ol type = 'a'>
                <li>First</li>
                <li>Second</li>
                <li>Third</li>
                <li>Fourth</li>
            </ol>
        </td>
        <td>10</td>
    </tr>
</table>
</body>
</html>
