<#import "parts/common.ftlh" as c>
<#import "parts/newbank.ftlh" as nb>
<#import "parts/deletebank.ftlh" as db>

<@c.page>
    <style>
        .btn-bank {
            border-bottom: solid 3px #eec111;
        }
        .income {
            background: #727a17;
            color: white;
            font-weight: bold;
        }
        .expenditure {
            background: #c04848;
            color: white;
            font-weight: bold;
        }
    </style>
    <#if message??>
        ${message}
    </#if>
    <div class="container">
        <#if ActivateNewBankForm>
            <@nb.newbank "/main/newbank" />
        </#if>
        <#if ActivateDeleteBankForm>
            <@db.deletebank "/main/deletebank"/>
        </#if>
        <#if (ActivateNewBankForm == false) && (ActivateDeleteBankForm == false)>
            <div class="row">
                <div class="col-md-5 col-lg-6">
                    <form method="get">
                        <button type="submit"
                                name="newbank"
                                value="yes"
                                class="btn btn-xs btn-bank">
                            <span style="font-weight: normal">Додати новий рахунок</span>
                        </button>
                        <!--button type="submit"
                                name="editbank"
                                value="yes"
                                class="btn btn-xs btn-bank">
                            <span style="font-weight: normal">Редагувати поточний рахунок</span>
                        </button-->
                        <button type="submit"
                                name="deletebank"
                                value="yes"
                                class="btn btn-xs btn-bank">
                            <span style="font-weight: normal">Видалити поточний рахунок</span>
                        </button>
                    </form>
                </div>
            </div>
        </#if>
    </div>
    <br>
    <div class="row">
        <div class="col-md-6 col-lg-4 col-12">
            <h2><b>Стани рахунків</b></h2>
            <h4>Всього <b>${TotalScore}</b>₴</h4>
            <div class="row">
                <#list banks as bank>
                    <h6 class="col-md-6 col-lg-6 col-6 mt-1">${bank.getTitle_bank()} </h6>
                    <h4 class="col-md-6 col-lg-6 col-6">${bank.getScore()}₴</h4>
                </#list>
            </div>
        </div>
        <div class="col-lg-8">
            <table class="table table-sm">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Назва категорії</th>
                    <th scope="col">Рахунок</th>
                    <th scope="col">Витрата/Дохід</th>
                    <th scope="col">Дата</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Рознічний клієнт</td>
                    <td>Mono</td>
                    <td><span class="income">+25000</span></td>
                    <td>22-10-2021</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Запчастини</td>
                    <td>Privat24</td>
                    <td><span class="expenditure">-2300</span></td>
                    <td>20-10-2021</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>