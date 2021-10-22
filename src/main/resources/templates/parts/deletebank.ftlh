<#macro deletebank path>
    <style>
        a, a:hover, a:focus {
            outline: none;
            text-decoration: none;
        }

        .TitleBankText {
            color: #7f8291;
            font-weight: bold;
        }

        .ListTitleBank {
            color: white;
        }

    </style>
    <div class="row">
        <div class="col-md-12 col-lg-8 col-12" style="background: linear-gradient(#2e3a6a,#2f0b45);">
            <div class="row">
                <div class="col-md-1 col-1"></div>
                <div class="col-md-4 col-6 pt-3 pb-3">
                    <h5 style="color: #eec111; font-weight: bold">Видалити рахунок</h5>
                </div>
            </div>
            <br>
            <div class="row">
                <form action="${path}" method="post">
                    <div class="form-group row">
                        <div class="col-md-1 col-1"></div>
                        <label class="col-form-label col-md-3 col-3 TitleBankText">Назва рахунку:</label>
                        <div class="col-md-6 form-check">
                            <#list banks as bank>
                                <#if bank.getTitle_bank() == "Головний рахунок">
                                    <input class="form-check-input"
                                           type="checkbox"
                                           name="${bank.getTitle_bank()}"
                                           id="TitleBankId${bank?counter}"
                                           disabled>
                                    <label for="TitleBankId${bank?counter}"
                                           class="form-check-label"
                                           style="color: #7f8291">
                                        <h5>${bank.getTitle_bank()}</h5>
                                    </label>
                                <#else >
                                    <input class="form-check-input"
                                           type="checkbox"
                                           name="${bank.getTitle_bank()}"
                                           id="TitleBankId${bank?counter}">
                                    <label for="TitleBankId${bank?counter}">
                                        <h5 class="ListTitleBank">${bank.getTitle_bank()}</h5>
                                    </label>
                                </#if>
                                <br>
                            </#list>
                            <br>
                        </div>
                        <div class="row">
                            <div class="col-md-1 col-1"></div>
                            <div class="col-md-11 col-11">
                                <a href="/main" class="btn btn-xs orange col-lg-2 col-md-4 col-5">Скасувати</a>
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-xs green col-lg-2 col-md-4 col-5">
                                    Зберегти
                                </button>
                            </div>
                        </div>
                    </div>
                    <br>
                </form>
            </div>
            <br>
        </div>
    </div>
</#macro>