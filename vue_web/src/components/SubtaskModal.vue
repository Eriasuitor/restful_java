<template>
    <div class="ui modal phase" id='phase'>
        <i class="close icon black"></i>
        <div class="header">
            新建阶段任务
        </div>
        <div class="content">
            <form class="ui form">
                <div class="field">
                    <label>阶段任务</label>
                    <input type="text" v-model="phase.name">
                </div>
                <div class="field">
                    <label>描述</label>
                    <textarea rows="6" maxlength="800" v-model="phase.description"></textarea>
                </div>

                <div class="fields">
                    <div class="eight wide field" @click="showDropdown('phase_manger', 1)">
                        <label>
                            负责人
                        </label>
                        <div class="ui fluid multiple search selection dropdown phase_manger">
                            <input type="hidden" name="receipt">
                            <i class="dropdown icon"></i>
                            <div class="default text"></div>
                        </div>
                    </div>
                </div>
                <div class="fields">
                    <div class="eight wide field">
                        <label>
                            开始时间
                        </label>
                        <input type="date" v-model="phase.startDate">
                    </div>
                    <div class="eight wide field">
                        <label>
                            结束时间
                        </label>
                        <input type="date" v-model="phase.endDate">
                    </div>
                </div>
            </form>
        </div>
        <div class="actions">
            <div class="ui positive right button" @click="newPhase">
                新建
            </div>
        </div>
    </div>
</template>

<script>
export default {

}
</script>
