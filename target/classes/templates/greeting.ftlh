<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="row">
        <div class="col-md-12 col-11">
            <h1>Фінансовий облік від <b>Uspih</b></h1>
            <h4>Для ведення фінансового обліку в особистих і бізнес цілях</h4>
            <h4>Простий і зручний у використанні</h4>
        </div>
    </div>

    <br>

    <#if name == "unknow">
        <div class="row">
            <div class="col-md-3 col-lg-2 mt-2">
                <a href="/login" class="btn btn-xs blue col-md-12 col-5" style="color: white;">Вхід</a>
            </div>
            <div class="col-md-3 col-lg-2 mt-2">
                <a href="/registration" class="btn btn-xs blue col-md-12 col-5" style="color: white;">Реєстрація</a>
            </div>
        </div>
    </#if>
</@c.page>
