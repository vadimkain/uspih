<#macro newcategory path>
    <form action="${path}" method="post">
        <h2>Нова категорія</h2>
        <br>
        <div class="form-group row">
            <label for="inputTitle" class="col-sm-2 col-form-label"><b>Назва категорії</b></label>
            <div class="col-sm-5">
                <input type="text"
                       class="form-control TitleCategory ${(TitleCategoryError??)?string('is-invalid', '')}"
                       id="inputTitle"
                       name="title_category"
                       maxlength="30"
                       placeholder="Тільки букви" required>
                <#if TitleCategoryError??>
                    <div class="invalid-feedback">
                        ${TitleCategoryError}
                    </div>
                </#if>
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
        document.querySelector('.TitleCategory').addEventListener('keyup', function () {
            this.value = this.value.replace(/[-,./?';":!@#$%^&*()/_=+~*{\[}\]|`№<>]/g, '');
        });
    </script>
</#macro>