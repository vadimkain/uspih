<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <div class="row">
        <!-- Если есть сессия (для неавторизованного пользователя её не сущесвтует) и в сессии есть ошибка -->
        <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
            <!-- Выводим alert -->
            <div class="col-lg-2"></div>
            <div class="alert alert-danger col-12 col-md-12 col-lg-8" role="alert">
                <#if Session.SPRING_SECURITY_LAST_EXCEPTION.message == "Bad credentials">
                    Неверно введён логин или пароль
                <#else >
                    Session.SPRING_SECURITY_LAST_EXCEPTION.message
                </#if>
            </div>
            <div class="col-lg-2"></div>
        </#if>
        <#if message??>
            <!-- Выводим alert -->
            <div class="col-lg-2"></div>
            <div class="alert alert-${messageType} col-12 col-md-12 col-lg-8" role="alert">
                ${message}
            </div>
            <div class="col-lg-2"></div>
        </#if>
        <@l.login "/login" false />
    </div>
</@c.page>
