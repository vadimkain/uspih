<#import "parts/common.ftlh" as c>
<#import "parts/newbank.ftlh" as nb>
<#import "parts/deletebank.ftlh" as db>
<#import 'parts/newmoney.ftlh' as nw>
<#import 'parts/newcategory.ftlh' as nc>
<#import 'parts/deletecategory.ftlh' as dc>

<@c.page>
    <style>
        @import url("https://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css");

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

        i {
            font-family: fontawesome !important;
            font-style: normal;
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
        <#if ActivateNewMoneyForm>
            <@nw.newmoney "/main/newmoney"/>
        </#if>
        <#if ActivateNewCategoryForm>
            <@nc.newcategory "/main/newcategory"/>
        </#if>
        <#if AcivateDeleteCategoryForm>
            <@dc.deletecategory "/main/deletecategory" />
        </#if>
        <#if
        (ActivateNewBankForm == false) &&
        (ActivateDeleteBankForm == false) &&
        (ActivateNewMoneyForm == false) &&
        (ActivateNewCategoryForm == false) &&
        (AcivateDeleteCategoryForm == false)
        >
            <div class="row">
                <div class="col-md-12 col-lg-12">
                    <form method="get">
                        <button type="submit"
                                name="newbank"
                                value="yes"
                                class="btn btn-xs btn-bank">
                            <span style="font-weight: normal">Додати новий рахунок</span>
                        </button>
                        <button type="submit"
                                name="deletebank"
                                value="yes"
                                class="btn btn-xs btn-bank">
                            <span style="font-weight: normal">Видалити поточний рахунок</span>
                        </button>
                        <button type="submit"
                                name="newmoney"
                                value="yes"
                                class="btn btn-xs btn-bank">
                            <span style="font-weight: normal">Додати витрату або дохід</span>
                        </button>
                        <button type="submit"
                                name="newcategory"
                                value="yes"
                                class="btn btn-xs btn-bank">
                            <span style="font-weight: normal">Додати категорію</span>
                        </button>
                        <button type="submit"
                                name="deletecategory"
                                value="yes"
                                class="btn btn-xs btn-bank">
                            <span style="font-weight: normal">Видалити категорію</span>
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
            <a href="/transaction-archive" class="link-primary">Переглянути архів транзакцій</a>
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
                <form action="/main/deletetransaction" method="post">
                    <#list transactionsList as tr>
                        <tr>
                            <th scope="col">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button style="
                                            border: none;
                                            outline: none;
                                            background: none;"
                                        type="submit"
                                        name="deletetransaction"
                                        value="${tr.getId()}">
                                    <i class="fas fa-minus-square"></i>
                                </button>
                            </th>
                            <td>${tr.getCategory()}</td>
                            <td>${tr.getBank()}</td>
                            <td>
                            <span class="${(tr.getMoney() > 0)?string('income', 'expenditure')}">
                                ${(tr.getMoney() > 0)?string('+', '')}${tr.getMoney()}
                            </span>
                            </td>
                            <td>${tr.getLocalDate()}</td>
                        </tr>
                    </#list>
                </form>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>