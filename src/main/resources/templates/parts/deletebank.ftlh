<#macro deletebank path>
    <form action="${path}" method="post">
        <h2>Видалити рахунок</h2>
        <br>
        <br>
        <fieldset class="form-group">
            <div class="row">
                <legend class="col-form-label col-sm-2 pt-0"><b>Назва рахунку</b></legend>
                <div class="col-sm-10">
                    <#list banks as bank>
                        <div class="form-check">
                            <#if bank.getTitle_bank() == "Головний рахунок">
                                <input class="form-check-input"
                                       type="checkbox"
                                       name="${bank.getTitle_bank()}"
                                       id="TitleBankId${bank?counter}"
                                       disabled>
                                <label for="TitleBankId${bank?counter}"
                                       class="form-check-label"
                                       style="color: #7f8291">
                                    ${bank.getTitle_bank()}
                                </label>
                            <#else >
                                <input class="form-check-input"
                                       type="checkbox"
                                       name="${bank.getTitle_bank()}"
                                       id="TitleBankId${bank?counter}">
                                <label for="TitleBankId${bank?counter}">
                                    ${bank.getTitle_bank()}
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