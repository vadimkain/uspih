<#macro deletebank path>
    <div class="row">
        <div class="col-md-12 col-lg-8 col-12" style="background: linear-gradient(#2e3a6a,#2f0b45);">
            <div class="row">
                <div class="col-md-1 col-1"></div>
                <div class="col-md-4 col-6 pt-3 pb-3">
                    <h5 style="color: #eec111; font-weight: bold">Видалити рахунок</h5>
                </div>
            </div>
            <br>
            <div class="row">
                <form action="${path}" method="post">
                    <div class="form-group row">
                        <div class="col-md-1 col-1"></div>
                        <label class="col-form-label col-md-3 col-3">Назва рахунку:</label>
                        <div class="col-md-7 col-8">
                        </div>
                    </div>
                </form>
            </div>
            <br>
        </div>
    </div>
</#macro>