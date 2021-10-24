<#import "parts/common.ftlh" as c>
<@c.page>
    <style>
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

    <div class="d-flex justify-content-center">
        <h3>Архів транзакцій</h3>
    </div>
    <br>
    <div class="row">
        <div class="col-lg-2"></div>
        <div class="d-flex justify-content-center col-lg-8">
            <table class="table table-sm">
                <thead>
                <tr>
                    <th scope="col">Назва категорії</th>
                    <th scope="col">Рахунок</th>
                    <th scope="col">Витрата/Дохід</th>
                    <th scope="col">Дата</th>
                </tr>
                </thead>
                <tbody>
                <#list ArchivedTransactions as ar>
                    <tr>
                        <td>${ar.getCategory()}</td>
                        <td>${ar.getTitle_bank()}</td>
                        <td>
                            <span class="${(ar.getMoney() > 0)?string('income', 'expenditure')}">
                                ${(ar.getMoney() > 0)?string('+', '')}${ar.getMoney()}
                            </span>
                        </td>
                        <td>${ar.getLocalDate()}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-lg-2"></div>
    </div>
</@c.page>