<#macro newbank path>
    <style>
        a, a:hover, a:focus {
            outline: none;
            text-decoration: none;
        }

        label {
            color: #7f8291;
            font-weight: bold;
        }

        .isInput {
            border-radius: 20px;
            background: rgba(255, 255, 255, 0.2);
            border: none;
            font-weight: bold;
            color: #eec111;
        }

        .isInput:hover {
            box-shadow: 0px 0px 10px rgba(255, 255, 255, 0.2);
            font-weight: bold;
            color: #eec111;
        }

        .isInput:focus {
            background: rgba(255, 255, 255, 0.2);
            box-shadow: 0px 0px 10px rgba(255, 255, 255, 0.2);
            font-weight: bold;
            color: #eec111;
        }

        .btn-y {
            font-weight: bold;
            background: #eec111;
            color: white;
        }

        .btn-y:hover {
            font-weight: bold;
        }
    </style>

    <div class="row">
        <div class="col-md-12 col-lg-8 col-12" style="background: linear-gradient(#2e3a6a,#2f0b45);">
            <div class="row">
                <div class="col-md-1 col-1"></div>
                <div class="col-md-4 col-6 pt-3 pb-3">
                    <h5 style="color: #eec111; font-weight: bold">Новий рахунок</h5>
                </div>
            </div>
            <br>
            <div class="row">
                <form action="${path}" method="post">
                    <div class="form-group row">
                        <div class="col-md-1 col-1"></div>
                        <label class="col-form-label col-md-3 col-3">Назва рахунку:</label>
                        <div class="col-md-7 col-8">
                            <input type="text"
                                   name="title_bank"
                                   class="form-control isInput ${(TitleBankError??)?string('is-invalid', '')}"
                                   maxlength="20">
                            <#if TitleBankError??>
                                <div class="invalid-feedback">
                                    ${TitleBankError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <br>
                    <div class="form-group row">
                        <div class="col-md-1 col-1"></div>
                        <label class="col-form-label col-md-3 col-3">Поточний рахунок:</label>
                        <div class="col-md-7 col-8">
                            <input type="text"
                                   name="score"
                                   class="form-control isInput ValidateScore ${(ScoreBankError??)?string('is-invalid', '')}"
                                   maxlength="80">
                            <#if ScoreBankError??>
                                <div class="invalid-feedback">
                                    ${ScoreBankError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <br>
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
                </form>
            </div>
            <br>
        </div>
    </div>
    <script>
        document.querySelector('.ValidateScore').addEventListener('keyup', function () {
            this.value = this.value.replace(/[^\d.,-]/g, '');
        });
    </script>
</#macro>