<#macro deletecategory path>
    <form action="${path}" method="post">
        <h2>Видалити категорію</h2>
        <br>
        <br>
        <fieldset class="form-group">
            <div class="row">
                <legend class="col-form-label col-sm-2 pt-0"><b>Назва категорії</b></legend>
                <div class="col-sm-10">
                    <#list categories as category>
                        <div class="form-check">
                            <#if category.getTitle_category() == "Зарплата">
                                <input class="form-check-input"
                                       type="checkbox"
                                       name="${category.getTitle_category()}"
                                       id="TitleCategory${category?counter}"
                                       disabled>
                                <label for="TitleCategory${category?counter}"
                                       class="form-check-label"
                                       style="color: #7f8291">
                                    ${category.getTitle_category()}
                                </label>
                            <#else >
                                <input class="form-check-input"
                                       type="checkbox"
                                       name="${category.getTitle_category()}"
                                       id="TitleCategory${category?counter}">
                                <label for="TitleCategory${category?counter}">
                                    ${category.getTitle_category()}
                                </label>
                            </#if>
                        </div>
                    </#list>
                </div>
            </div>
        </fieldset>
        <br>
        <div class="form-group row">
            <div class="col-sm-10">
                <a href="/main" class="btn btn-xs orange col-lg-2 col-md-4 col-5">Скасувати</a>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-xs green col-lg-2 col-md-4 col-5">Зберегти</button>
            </div>
        </div>
    </form>
</#macro>