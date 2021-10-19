<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="row my-2">
        <div class="col-md-3">
            <form method="get" action="/main" class="input-group">
                <input type="text" name="filter" value="${filter!}" class="form-control" placeholder="Найти по тэгу">
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>

        <div class="my-2">
            <a class="btn btn-secondary col-md-3" data-bs-toggle="collapse" href="#collapseExample" role="button"
               aria-expanded="false"
               aria-controls="collapseExample">
                Добавить новое сообщение
            </a>
            <div class="collapse <#if message??>show</#if>" id="collapseExample">
                <div class="card card-body">
                    <form method="post" enctype="multipart/form-data" class="col-md-6">
                        <!-- $textError??string() Если есть ошибка, вернём логическое значение, которое приведёт к строке-->
                        <input type="text" name="text" placeholder="Введите сообщение"
                               class="form-control my-2 ${(textError??)?string('is-invalid', '')}"
                               value="<#if message??>${message.text}</#if>"/>
                        <#if textError??>
                            <div class="invalid-feedback">
                                ${textError}
                            </div>
                        </#if>
                        <input type="text" name="tag" placeholder="Тэг" class="form-control my-2"
                               value="<#if message??>${message.text}</#if>"/>
                        <#if tagError??>
                            <div class="invalid-feedback">
                                ${tagError}
                            </div>
                        </#if>
                        <input type="file" name="file" class="form-control my-2">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-primary my-2">Добавить</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <#list messages as message>
            <div class="card my-3 mx-3" style="width: 18rem;">
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if>
                <div class="m-2">
                    <span>${message.text}</span>
                </div>
                <div class="card-footer text-muted">
                    ${message.authorName}
                    <#if message.tag != "">
                        - <i>${message.tag}</i>
                    </#if>
                </div>
            </div>
        <#else >
            No messages

        </#list>
    </div>
</@c.page>