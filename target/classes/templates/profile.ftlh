<#import "parts/common.ftlh" as c>

<@c.page>
    <h1>Смена пароля/почты</h1>
    <h3>${username}</h3>
    ${message!}
    <form method="post">
        <div class="mb-2 form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control"/>
            </div>
        </div>
            <div class="mb-2 form-group row">
                <label class="col-sm-2 col-form-label"> email: </label>
                <div class="col-sm-6">
                    <!-- value="$email!''" если почта не задана, то отображается пустая строчка -->
                    <input type="email" name="email" class="form-control" placeholder="some@some.com" value="${email!''}"/>
                </div>
            </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" value="Sign In" class="mb-2 btn btn-primary">Save</button>
    </form>
</@c.page>