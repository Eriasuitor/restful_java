<template>
    <div2>
        <div class="ui breadcrumb">
            <a class="section">JIRA</a>
            <div class="divider"> / </div>
            <a class="section" @click="routeTo('/projects')">Projects</a>
            <div class="divider"> / </div>
            <a class="section" @click="routeTo('/projects/' + project.id)">{{project.name}}</a>
            <div class="divider"> / </div>
            <div class="active section">{{subtask.name}}</div>
        </div>
        <h3 class="ui dividing header">{{subtask.name}}
            <div class="sub header">{{subtask.description}}</div>
    
        </h3>
        <div class="ui two column very relaxed grid">
            <div class="column">
                <button class="ui basic button" @click='logEvent'>
                                            <i class="icon plus"></i>Log
                                        </button>
                <div class="ui comments">
                    <div class="ui comment" v-for="log in logs" :key="log.id">
                        <div class="avatar">
                            <img :src="log.staff.image">
                        </div>
                        <div class="content">
                            <a class="author">{{log.staff.name}}</a>
                            <div class="metadata">
                                <span class="date">于 {{diffTime(log.insertDate)}}前 Log {{log.timeCost}} 小时</span>
                            </div>
                            <div class="text">{{log.note}} </div>
                            <div class="actions">
                                <!-- <a class="reply">编辑</a> -->
                                <a class="save" @click="deleteLog(log.id)">删除</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    
            <div class="column">
    
                <div class="ui blue image label"><img :src="subtask.managerStaff.image"> {{subtask.managerStaff.name}}
                    <div class="detail">负责人</div>
                </div>
                <div class="ui statistics">
                    <div class="statistic">
                        <div class="value">
                            {{subtask.completed}} %
                        </div>
                        <div class="label">完成</div>
                    </div>
                    <div class="statistic">
                        <div class="value">{{subtask.timeCost}} H</div>
                        <div class="label">时间花费</div>
                    </div>
                    <div class="statistic">
                        <div class="value">{{computeEcoCost()}} 元</div>
                        <div class="label">费用产生</div>
                    </div>
                    <div class="statistic">
                        <div class="value">{{logs.length}}</div>
                        <div class="label">Logs</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui cards">
            <div class="card" v-for="_bug in bugs" :key="_bug.id">
                <!-- <span class="ui right corner label">
                    <i class="warning red icon"></i>
                  </span> -->
                <div class="content">
                    <div class="header">{{_bug.name}}
                        <div class="note" v-if="_bug.project != null">来自项目：{{_bug.project.name}}
                        </div>
                        <div class="note" v-if="_bug.subtask != null">来自子任务：{{_bug.subtask.name}}
                        </div>
                        <div class="note" v-if="_bug.assignedStaff != null">分配于：{{_bug.assignedStaff.name}}
                        </div>
                    </div>
                    <div class="description">{{_bug.description}}</div>
                    <div class="ui feed" v-if="_bug.lasEditStaff != null">
                        <div class="event">
                            <div class="label">
                                <img :src="_bug.lasEditStaff.image">
                            </div>
                            <div class="content">
                                <div class="summary">{{_bug.lasEditStaff.name}}</div>
                                <div class="extra text">{{_bug.note}}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ui bottom attached buttons">
                    <div class="ui button disabled"><i class="doctor icon"></i>已关闭</div>
                </div>
            </div>
        </div>
        <div class="ui modal newLog" id='newLog'>
            <i class="close icon black"></i>
            <div class="header">
                New Log
            </div>
            <div class="content">
                <form class="ui form">
                    <div class="fields">
                        <div class="eight wide field required">
                            <label>
                                                        开始时间
                                                    </label>
                            <input type="date" v-model="log.startDate">
                        </div>
                        <div class="eight wide field required">
                            <label>
                                                        结束时间
                                                    </label>
                            <input type="date" v-model="log.endDate">
                        </div>
                        <div class="eight wide field required">
                            <label>
                                        用时
                                    </label>
                            <div class="ui right labeled input">
                                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.timeCost">
                                <div class="ui basic label">小时 </div>
                            </div>
                        </div>
                    </div>
                    <div class="fields">
                        <div class="eight wide field required">
                            <label>
                                                        完成度
                                                    </label>
                            <div class="ui right labeled input">
                                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.completed">
                                <div class="ui basic label">% </div>
                            </div>
                        </div>
                        <div class="eight wide field">
                            <label>
                                                        分配于
                                                    </label>
                            <div class="ui fluid multiple search selection dropdown log_assign staffDropdown">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                        <div class="eight wide field">
                            <label>
                                                        花费
                                                    </label>
                            <div class="ui right labeled input">
                                <input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" v-model="log.economicCost">
                                <div class="ui basic label">元 </div>
                            </div>
                        </div>
                    </div>
                    <div class="field">
                        <label>备注</label>
                        <textarea rows="6" maxlength="800" v-model="log.note"></textarea>
                    </div>
                </form>
            </div>
            <div class="actions">
                <div class="ui red deny button">
                    取消
                </div>
                <div class="ui positive right button">
                    Log
                </div>
            </div>
            <div v-bind:class="{ui:true, dimmer:true, inverted:true}">
                <div class="ui text loader ">保存中</div>
            </div>
        </div>
    </div2>
</template>

<script>
    export default {
        data() {
            return {
                subtask: {
                    assignedStaff: {},
                    managerStaff: {}
                },
                log: {},
                logs: [],
                project: JSON.parse(window.localStorage.getItem('project')),
                token: "",
                userInfo: {},
                bugs: []
            }
        },
        created() {
            window.localStorage.setItem('toLogin', this.$route.path)
            if (!window.localStorage.getItem('token') || !window.localStorage.getItem('userInfo')) {
                window.localStorage.removeItem('token')
                window.localStorage.removeItem('userInfo')
                this.$router.push({
                    path: '/login',
                    replace: true
                })
            } else {
                this.userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
                this.token = window.localStorage.getItem('token')
                this.refreshLogs()
                this.refreshBugs()
                if (this.subtask.id === undefined) {
                    this.refreshSubtasks()
                }
            }
        },
        methods: {
            deleteLog: function(id) {
                this.$api.delete(
                    this.$apiUrl +
                    '/projects/' +
                    this.$route.params.id +
                    '/logs/' + id,
                    null,
                    data => {
                        window.alert(data.information)
                        this.refreshLogs()
                    }
                )
            },
            refreshBugs: function() {
                this.$api.delete(
                    this.$apiUrl +
                    '/projects/' +
                    this.$route.params.id +
                    '/bugs/?subId=' + this.$route.params.subId + "&token=" + this.token,
                    null,
                    data => {
                        if (data.successful) {
                            this.bugs = data.results
                        } else {
                            window.alert(data.information)
                        }
                    }
                )
            },
            refreshSubtasks: function() {
                this.$api.get(
                    this.$apiUrl +
                    '/projects/' +
                    this.$route.params.id +
                    '/subtasks/' +
                    this.$route.params.subId + "?token=" + this.token,
                    null,
                    data => {
                        if (data.responseCode === 401) {
                            window.localStorage.removeItem('token')
                            window.localStorage.removeItem('userInfo')
                            this.$router.push({
                                path: '/login',
                                replace: true
                            })
                        } else {
                            if (data.successful) {
                                this.subtask = data.object
                            } else {
                                window.alert(data.information)
                            }
                        }
    
                    }
                )
            },
            computeEcoCost: function() {
                var tot = 0
                for (var i = 0; i < this.logs.length; i++) {
                    tot += this.logs[i].economicCost
                }
                return tot
            },
            refreshLogs: function() {
                this.$api.get(
                    this.$apiUrl +
                    '/projects/' +
                    this.$route.params.id +
                    '/logs?subId=' +
                    this.$route.params.subId + "&token=" + this.token,
                    null,
                    data => {
                        if (data.responseCode === 401) {
                            window.localStorage.removeItem('token')
                            window.localStorage.removeItem('userInfo')
                            this.$router.push({
                                path: '/login',
                                replace: true
                            })
                        } else {
                            if (data.successful) {
                                this.logs = data.results
                            } else {
                                window.alert(data.information)
                            }
                        }
                    }
                )
            },
            routeTo: function(path) {
                window.localStorage.setItem('project', JSON.stringify(this.project))
                this.$router.push(path)
            },
            postLog: function() {
                this.log.assignedID = $('.dropdown.log_assign').dropdown('get value')
                this.log.subtaskID = this.$route.params.subId
                console.log(this.log)
    
                if (!this.log.startDate) {
                    window.alert('请填写开始时间');
                    return false;
                }
    
                if (!this.log.endDate) {
                    window.alert('请填写结束时间');
                    return false;
                }
                if (this.log.startDate > this.log.endDate) {
                    window.alert('结束时间不得早于开始时间');
                    return false;
                }
                if (!this.log.timeCost) {
                    window.alert('请填写耗时')
                    return false
                }
                if (!this.log.completed) {
                    window.alert('请填写完成度')
                    return false
                }
    
                if (parseInt(this.log.completed) + this.completed > 100) {
                    window.alert(`此次Log完成度最大值为 ${100 - this.completed}%`)
                    return false
                }
                this.$api.post(
                    this.$apiUrl + '/projects/' + this.$route.params.id + '/logs?token=' + this.token,
                    this.log,
                    data => {
                        if (data.responseCode === 401) {
                            window.localStorage.removeItem('token')
                            window.localStorage.removeItem('userInfo')
                            this.$router.push({
                                path: '/login',
                                replace: true
                            })
                        } else {

                            if (data.successful) {
                                this.refreshLogs()
                            }
                            window.alert(data.information)
                        }
                    }
                )
                return true
            },
            diffTime: function(date) {
                var diffMS = new Date().getTime() - new Date(date).getTime()
                if (diffMS >= 86400000) {
                    return parseInt(diffMS / 86400000) + ' 天'
                } else if (diffMS >= 3600000) {
                    return parseInt(diffMS / 3600000) + ' 小时'
                } else if (diffMS >= 60000) {
                    return parseInt(diffMS / 36000) + ' 分钟'
                } else {
                    return parseInt(diffMS / 1000) + ' 秒'
                }
            },
            logEvent: function() {
                $('.newLog')
                    .modal({
                        centered: true,
                        blurring: false,
                        inverted: false,
                        closable: false,
                        context: 'div2',
                        onApprove: () => {
                            return this.postLog()
                        }
                    })
                    .modal('show')
            }
        },
        mounted() {
            $('.staffDropdown').dropdown({
                apiSettings: {
                    url: this.$apiUrl + '/staff?name={query}'
                },
                maxSelections: 1
            })
        }
    }
</script>

<style scoped>
    
</style>
