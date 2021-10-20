<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-primary bg-opacity-10">
    <div class="container-fluid">
        <div class="col-md-8 col-5">
            <a class="navbar-brand" href="/">Uspih - finance</a>
        </div>
        <#if name != "unknow">
            <div class="row col-md-4 col-7">
                <div class="navbar-text col-md-2 col-3">${name}</div>
                <div class="col-md-5 col-6"><@l.logout /></div>
            </div>
        </#if>
    </div>
</nav>