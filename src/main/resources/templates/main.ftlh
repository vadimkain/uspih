<#import "parts/common.ftlh" as c>
<#import "parts/newbank.ftlh" as nb>

<@c.page>
    <#if message??>
        ${message}
    </#if>
    <div class="container">
        <#if ActivateNewBankForm>
            <@nb.newbank "/main/newbank" />
        </#if>
        <#if !ActivateNewBankForm>
            <div class="row">
                <div class="col-md-6">
                    <form method="get">
                        <button type="submit" name="newbank" value="yes" class="btn btn-xs blue">Додати новий рахунок
                        </button>
                    </form>
                </div>
            </div>
        </#if>
    </div>
    <br>
    <div class="row">
        <div class="col-md-4">
            <h3><b>Стани рахунків</b></h3>
        </div>
    </div>
</@c.page>