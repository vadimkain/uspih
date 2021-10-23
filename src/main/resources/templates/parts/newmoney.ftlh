<#macro newmoney path>
    <form action="${path}" method="post">
        <fieldset class="form-group">
            <div class="row">
                <legend class="col-form-label col-sm-2 pt-0"><b>Назва категорії</b></legend>
                <div class="col-sm-10">
                    <#list categories as category>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="category"
                                   value="${category.getTitle_category()}"
                                   id="category${category?counter}" required>
                            <label class="form-check-label" for="category${category?counter}">
                                ${category.getTitle_category()}
                            </label>
                        </div>
                    </#list>
                </div>
            </div>
        </fieldset>
        <br>
        <fieldset class="form-group">
            <div class="row">
                <legend class="col-form-label col-sm-2 pt-0"><b>Рахунок</b></legend>
                <div class="col-sm-10">
                    <#list banks as bank>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="bank" value="${bank.getTitle_bank()}"
                                   id="bank${bank?counter}" required>
                            <label class="form-check-label" for="bank${bank?counter}">
                                ${bank.getTitle_bank()}
                            </label>
                        </div>
                    </#list>
                </div>
            </div>
        </fieldset>
        <br>
        <div class="form-group row">
            <label for="inputMoney" class="col-sm-2 col-form-label"><b>Витрата/Дохід</b></label>
            <div class="col-sm-5">
                <input type="text"
                       class="form-control inputMoney ${(MoneyError??)?string('is-invalid', '')}"
                       id="inputMoney"
                       name="money"
                       placeholder="Якщо витрата - введіть спочатку мінус (-30500)" required>
                <#if MoneyError??>
                    <div class="invalid-feedback">
                        ${MoneyError}
                    </div>
                </#if>
            </div>
        </div>
        <br>
        <div class="form-group row">
            <label for="date" class="col-sm-2 col-form-label"><b>Дата</b></label>
            <div class="col-sm-5">
                <input type="date"
                       class="form-control"
                       id="date"
                       name="date"
                       placeholder="Якщо витрата - введіть спочатку мінус (-30500)" required>
            </div>
        </div>
        <br>
        <div class="form-group row">
            <div class="col-sm-10">
                <a href="/main" class="btn btn-xs orange col-lg-2 col-md-4 col-5">Скасувати</a>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-xs green col-lg-2 col-md-4 col-5">Зберегти</button>
            </div>
        </div>
    </form>
    <script>
        document.querySelector('.inputMoney').addEventListener('keyup', function () {
            this.value = this.value.replace(/[^\d.-]/g, '');
        });
    </script>
</#macro>