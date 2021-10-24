<#include "security.ftlh">
<#import "login.ftlh" as l>

<style>
    @media (max-width: 800px) {
        .navbar {
            border-radius: 0px;
            border-bottom: solid 3px #eec111;
        }
    }

    @media (min-width: 801px) {
        .navbar {
            border-radius: 20px;
            box-shadow: 0px 3px 5px #eec111;
        }
    }

    a {
        text-decoration: none;
        color: black;
    }

    a:hover {
        color: black;
    }

</style>

<br>
<div class="container">
    <nav class="navbar navbar-light bg-light justify-content-between">
        <div class="navbar-item"></div>
        <a class="navbar-brand" href="/">Uspih-finance</a>
        <#if name != "unknow">
            <a class="nav-item nav-link active text-primary" href="/main">Головна</a>
            <div class="navbar-item col-lg-6 col-md-2"></div>
            <div class="nav-item row">
                <div class="col-lg-6 col-md-6 col-6">
                    <p class="my-sm-0 navbar-item nav-link disabled">${name}</p>
                </div>
                <div class="my-sm-0 navbar-item col-lg-6 col-md-6 col-6">
                    <@l.logout />
                </div>
            </div>
        </#if>
        <div class="navbar-item"></div>
    </nav>
</div>