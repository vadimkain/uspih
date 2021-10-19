<#macro login path isRegisterForm>
    <style>
        a, a:hover, a:focus {
            outline: none;
            text-decoration: none;
        }

        .isNotRegisterForm1 {
            color: #eec111;
            font-weight: bold;
        }

        .isRegisterForm1 {
            color: #7f8291;
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

    <div class="container">
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-12 col-md-12 col-lg-8" style="background: linear-gradient(#2e3a6a,#2f0b45);">
                <div class="row">
                    <div class="col-md-1 col-1"></div>
                    <div class="col-md-2 col-4 pt-3 pb-3">
                        <a href="/login">
                            <h5 class="${(!isRegisterForm)?string('isNotRegisterForm1', 'isRegisterForm1')}">Вхід</h5>
                        </a>
                    </div>
                    <div class="col-md-6 col-6 pt-3 pb-3">
                        <a href="/registration">
                            <h5 class="${(isRegisterForm)?string('isNotRegisterForm1', 'isRegisterForm1')}">
                                Реєстрація</h5>
                        </a>
                    </div>
                </div>
                <br>
                <div class="row">
                    <form action="${path}" method="post">
                        <div class="form-group row">
                            <div class="col-md-1 col-1"></div>
                            <label class="col-form-label col-md-3 col-3">Логін:</label>
                            <div class="col-md-7 col-8">
                                <input type="text"
                                       name="username"
                                       class="form-control ${(usernameError??)?string('is-invalid', '')} isInput"
                                       value="<#if user??>${user.username}</#if>">
                                <#if usernameError??>
                                    <div class="invalid-feedback">
                                        ${usernameError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                        <br>
                        <div class="form-group row">
                            <div class="col-md-1 col-1"></div>
                            <label class="col-form-label col-md-3 col-3">Пароль:</label>
                            <div class="col-md-7 col-8">
                                <input type="password"
                                       name="password"
                                       class="form-control ${(passwordError??)?string('is-invalid', '')} isInput">
                                <#if passwordError??>
                                    <div class="invalid-feedback">
                                        ${passwordError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                        <br>
                        <#if isRegisterForm>
                            <div class="form-group row">
                                <div class="col-md-1 col-1"></div>
                                <label class="col-form-label col-md-3 col-3">Пароль ще раз:</label>
                                <div class="col-md-7 col-8">
                                    <input type="password"
                                           name="password2"
                                           class="form-control ${(password2Error??)?string('is-invalid', '')} isInput">
                                    <#if password2Error??>
                                        <div class="invalid-feedback">
                                            ${password2Error}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <br>
                            <div class="form-group row">
                                <div class="col-md-1 col-1"></div>
                                <label class="col-form-label col-md-3 col-3">Email:</label>
                                <div class="col-md-7 col-8">
                                    <input type="email"
                                           name="email"
                                           class="form-control ${(emailError??)?string('is-invalid', '')} isInput">
                                    <#if emailError??>
                                        <div class="invalid-feedback">
                                            ${emailError}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <br>
                            <div class="form-group row">
                                <div class="col-md-1"></div>
                                <div class="col-form-label col-md-3"></div>
                                <div class="row col-md-7 col-12">
                                    <div class="g-recaptcha"
                                         data-sitekey="6Lc9jNIcAAAAAN7arcsLf41f_b-wDkVnG2P7ODPA"></div>
                                    <br>
                                    <#if captchaError??>
                                        <p style="color: red">${captchaError}</p>
                                    </#if>
                                </div>
                            </div>
                            <br>
                        </#if>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <div class="form-group row">
                            <div class="col-md-4 col-2"></div>
                            <button type="submit"
                                    value="${(isRegisterForm)?string('Зареєструватися', 'Увійти')}"
                                    class="btn btn-y col-md-4 col-8">
                                ${(isRegisterForm)?string('Зареєструватися', 'Увійти')}
                            </button>
                            <div class="col-md-4 col-2"></div>
                        </div>
                        <br>
                    </form>
                </div>
            </div>
            <div class="col-lg-2"></div>
        </div>
    </div>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-secondary">Sign Out</button>
    </form>
</#macro>