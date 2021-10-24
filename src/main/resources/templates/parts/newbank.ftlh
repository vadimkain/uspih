<#macro newbank path>
    <form action="${path}" method="post">
        <h2>Новий рахунок</h2>
        <br>
        <div class="form-group row">
            <label for="inputTitle" class="col-sm-2 col-form-label"><b>Назва рахунку</b></label>
            <div class="col-sm-5">
                <input type="text"
                       class="form-control ${(TitleBankError??)?string('is-invalid', '')}"
                       id="inputTitle"
                       name="title_bank"
                       maxlength="80" required>
                <#if TitleBankError??>
                    <div class="invalid-feedback">
                        ${TitleBankError}
                    </div>
                </#if>
            </div>
        </div>
        <br>
        <div class="form-group row">
            <label for="inputMoney" class="col-sm-2 col-form-label"><b>Поточний рахунок</b></label>
            <div class="col-sm-5">
                <input type="text"
                       class="form-control ValidateScore ${(ScoreBankError??)?string('is-invalid', '')}"
                       id="inputMoney"
                       name="score"
                       placeholder="Якщо витрата - введіть спочатку мінус (-30500)"
                       maxlength="80" required>
                <#if ScoreBankError??>
                    <div class="invalid-feedback">
                        ${ScoreBankError}
                    </div>
                </#if>
            </div>
        </div>
        <br>
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
        document.querySelector('.ValidateScore').addEventListener('keyup', function () {
            this.value = this.value.replace(/[^\d.-]/g, '');
        });
    </script>
</#macro>