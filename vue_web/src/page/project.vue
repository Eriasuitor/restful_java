<template>
    <div2>
        <div class="ui breadcrumb">
            <a class="section">JIRA</a>
            <div class="divider"> / </div>
            <a class="section" @click="routeTo('/projects')">Projects</a>
            <div class="divider"> / </div>
            <div class="active section">{{project.name}}</div>
        </div>
        <div class="ui image icon right floated">
            <i :title="project.status === 'Created'?'开始项目开发进程': '结束项目'" v-if="userInfo.id === project.managerID" :class="['circular','power','link','icon',{'red': project.status === 'Processing'}]" @click="changeStatus()"></i>
            <i title="删除项目" v-if="userInfo.id === project.managerID" class="circular delete link icon" @click="deleteProject()"></i>
            <i title="编辑项目" class="circular edit outline link icon" @click="editProjectEvent()"></i>
        </div>
        <h3 class="ui dividing header">{{project.name}}
            <div class="sub header">{{project.description}}</div>
        </h3>
    
        <div class="ui secondary segment">
    
            <div class="ui">
                <div class="ui blue image label"><img :src="project.staff.image"> {{project.staff.name}}
                    <div class="detail">负责人</div>
                </div>
                <i v-show="userInfo.id === project.managerID" class="small circular edit outline link icon editManager popupTriger"></i>
                <div class="ui flowing popup top left transition hidden">
                    <div class="ui divided center aligned grid">
                        <div class="column">
                            <div>
                                负责人
                                <div class="ui flowing multiple search selection dropdown edit_manger staffDropdown">
                                    <input type="hidden" name="receipt">
                                    <i class="dropdown icon"></i>
                                    <div class="default text"></div>
                                </div>
                                <div class="ui button" @click="modifyManager()">保存</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    
            <p>日期：{{formatDate(project.startDate)}} 至 {{formatDate(project.endDate)}} 
                <span v-if="project.ur">代码库：<a :href="project.url">{{project.url}}</a></span>
                状态：{{project.status}}
            </p>
    
            <div class="ui">
                <div class="ui statistics">
                    <div class="statistic">
                        <div class="value">
                            {{project.completed}}%
                        </div>
                        <div class="label">完成</div>
                    </div>
                    <div class="statistic">
                        <div class="value">{{project.timeCost}}</div>
                        <div class="label">时间花费</div>
                    </div>
                    <div class="statistic">
                        <div class="value">{{project.economicCost}}元</div>
                        <div class="label">费用产生</div>
                    </div>
                    <div class="statistic">
                        <div class="value">{{project.staffList.length === 0? 0: project.staffList.length + 1}}</div>
                        <div class="label">参与人数</div>
                    </div>
                </div>
                <div class="ui image label" v-for="staff in project.staffList" :key="'staff' + staff.id"><img :src="staff.image"> {{staff.name}}
                    <i v-show="userInfo.id === project.managerID" class="delete icon" @click="removeParticipant(staff.id,staff.name)"></i>
                </div>
                <i v-show="userInfo.id === project.managerID" class="small circular add user link icon addParticipant popupTriger"></i>
                <div class="ui flowing popup top left transition hidden">
                    <div class="ui divided center aligned grid">
                        <div class="column">
                            <div>
                                姓名
                                <div class="ui flowing multiple search selection dropdown add_participant staffDropdown">
                                    <input type="hidden" name="receipt">
                                    <i class="dropdown icon"></i>
                                    <div class="default text"></div>
                                </div>
                                <div class="ui button" @click="addParticipant()">添加</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <i class="small circular plus link icon" @click="newPhaseEvent"></i>新建阶段任务
        <div class="scrollY">
            <table class="ui compact red table">
                <thead>
                    <tr>
                        <th class="two wide">阶段</th>
                        <th class="three wide">子任务</th>
                        <th class="four wide">完成度</th>
                        <th class="seven wide" id='temporalPlanning' ref='temporalPlanning'>时间规划</th>
                    </tr>
                </thead>
                <tbody>
                    <template v-for="phase in phases">
                                        <tr :key="'pha' + phase.id">
                                            <td :rowspan="phase.subtaskList.length + 2">{{phase.name}}
                                                <i class="small circular edit outline link icon"></i>
                                            </td>
                                        </tr>
                                        <tr v-for="subtask in phase.subtaskList" :key="'sub' + subtask.id">
                                            <td>
                                                <a class="link" @click="routeTo(project.id + '/subtasks/' + subtask.id)">{{subtask.name}}</a>
                                            </td>
                                            <td>
                                                <div class="ui progress" :id="'pro' + subtask.id">
                                                    <div class="bar">
                                                        <div class="progress"></div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <a v-for="(item, index) in dateList" :key="subtask.id + index" :title="'开始于' + formatDate(subtask.startDate) + '，结束于' + formatDate(subtask.endDate)" :class="{ 'ui': true, 'circular': true, 'label': true,'green':subtask.completed === 100, 'red': item >= new Date(subtask.startDate) && (item <= new Date(subtask.endDate) && setRed(subtask.id) || redMap[subtask.id] == undefined && setRed(subtask.id))}">{{item.getMonth() + '.' + item.getDate()}}</a>
                                            </td>
                                        </tr>
                                        <tr :key="'subAdd' + phase.id">
                                            <td>
                                                <i class="small circular plus link icon" @click="newSubtaskEvent(phase.id, phase.name)"></i>
                                            </td>
                                        </tr>
</template>
                </tbody>
            </table>
        </div>
        <div class="ui modal phase" id='phase'>
            <i class="close icon black"></i>
            <div class="header">
                新建阶段任务
            </div>
            <div class="content">
                <form class="ui form">
                    <div class="field required">
                        <label>阶段任务</label>
                        <input type="text" v-model="phase.name">
                    </div>
                    <div class="field">
                        <label>描述</label>
                        <textarea rows="6" maxlength="800" v-model="phase.description"></textarea>
                    </div>

                    <div class="fields required">
                        <div class="eight wide field">
                            <label>
                                负责人
                            </label>
                            <div class="ui fluid multiple search selection dropdown phase_manger staffDropdown">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                    </div>
                    <div class="fields required">
                        <div class="eight wide field">
                            <label>
                                开始时间
                            </label>
                            <input type="date"  v-model="phase.startDate">
                        </div>
                        <div class="eight wide field required">
                            <label>
                                结束时间
                            </label>
                            <input type="date" v-model="phase.endDate">
                        </div>
                    </div>
                </form>
            </div>
            <div class="actions">
                <div class="ui red deny button">
                    取消
                </div>
                <div class="ui positive right button">
                    新建
                </div>
            </div>
        </div>
        <div class="ui modal subtask" id='subtask'>
            <i class="close icon black"></i>
            <div class="header">
                新增子任务
            </div>
            <div class="content">
                <form class="ui form">
                    <div class="field required">
                        <label>任务</label>
                        <input type="text" v-model="subtask.name">
                    </div>
                    <div class="field">
                        <label>描述</label>
                        <textarea rows="6" maxlength="800" v-model="subtask.description"></textarea>
                    </div>

                    <div class="fields">
                        <div class="eight wide field required">
                            <label>
                                负责人
                            </label>
                            <div class="ui fluid multiple search selection dropdown subtask_manger staffDropdown">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                        <div class="eight wide field required">
                            <label>
                                分配于
                            </label>
                            <div class="ui fluid multiple search selection dropdown subtask_assign staffDropdown">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                        <div class="eight wide field required">
                            <label>
                                阶段
                            </label>
                            <div class="ui fluid selection search dropdown subtask_phase phaseDropdown">
                                <input type="hidden" name="phase">
                                <div class="default text">{{phaseName}}</div>
                                <div class="menu">
                                    <div v-for="phase in phases" :key="'optPhase' + phase.id" class="item" :data-value="phase.id">{{phase.name}}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="fields">
                        <div class="eight wide field required">
                            <label>
                                开始时间
                            </label>
                            <input type="date" :min="startDate" v-model="subtask.startDate">
                        </div>
                        <div class="eight wide field required">
                            <label>
                                结束时间
                            </label>
                            <input type="date" v-model="subtask.endDate">
                        </div>
                    </div>
                </form>
            </div>
            <div class="actions">
                <div class="ui red deny button">
                    取消
                </div>
                <div class="ui positive right button">
                    新增
                </div>
            </div>
        </div>
        <div class="ui modal editProject">
            <i class="close icon black"></i>
            <div class="header">
                更改项目
            </div>
            <div class="content">
                <form class="ui form">
                    <div class="field required">
                        <label>项目名称</label>
                        <input type="text" v-model="projectToPut.name">
                    </div>
                    <div class="field">
                        <label>描述</label>
                        <textarea rows="6" maxlength="800" v-model="projectToPut.description"></textarea>
                    </div>

                    <div class="fields">

                        <div class="four wide field required">
                            <label>
                                开始时间
                            </label>
                            <input type="date" :value="startDate" ref='projectStartDate'>
                        </div>
                        <div class="four wide field required">
                            <label>
                                结束时间
                            </label>
                            <input type="date" :value="endDate" ref='projectEndDate'>
                        </div>
                        <div class="eight wide field">
                            <label>
                                代码库地址
                            </label>
                            <input type="url" v-model="projectToPut.url">
                        </div>
                    </div>
                </form>
            </div>
            <div class="actions">
                <div class="ui red deny button">
                    取消
                </div>
                <div class="ui positive right button">
                    更改
                </div>
            </div>
            <div v-bind:class="{ui:true, dimmer:true, inverted:true, active:isSaving}">
                <div class="ui text loader ">保存中</div>
            </div>
        </div>
    </div2>
</template>

<script>
    export default {
        components: {},
        data() {
            return {
                staffExcept: [],
                sq: null,
                iterval: 5,
                userId: 2,
                projectToPut: {
                    startDate: null,
                    endDate: null
                },
                phases: {},
                phase: {},
                subtask: {},
                sub_phaseId: 0,
                phaseName: 0,
                project: {
                    name: '载入中...',
                    description: '载入中...',
                    staff: {},
                    startDate: 0,
                    endDate: 0,
                    completed: 0,
                    timeCost: 0,
                    economicCost: 0,
                    staffList: []
                },
                dateNum: 0,
                dateList: [],
                redMap: {},
                test: '2018-04-04',
                isSaving: false,
                token: '',
                userInfo: {}
            }
        },
        created() {
            if (!window.localStorage.getItem('token') || !window.localStorage.getItem('userInfo')) {
                window.localStorage.setItem('toLogin', this.$route.path)
                window.localStorage.removeItem('token')
                window.localStorage.removeItem('userInfo')
                this.$router.push({
                    path: '/login',
                    replace: true
                })
            } else {
                this.userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
                this.token = window.localStorage.getItem('token')
                this.refreshPhases()
                if (this.project.id === undefined) {
                    this.$api.get(
                        this.$apiUrl + '/projects/' + this.$route.params.id + "?token=" + this.token,
                        null,
                        data => {
                            if (data.responseCode === 401) {
                                window.localStorage.setItem('toLogin', this.$route.path)
                                window.localStorage.removeItem('token')
                                window.localStorage.removeItem('userInfo')
                                this.$router.push({
                                    path: '/login',
                                    replace: true
                                })
                            } else {
                                if (data.successful) {
                                    this.project = data.project
                                    this.getDateList()
                                } else {
                                    window.alert(data.information)
                                }
                            }
                        }
                    )
                } else {
                    this.getDateList()
                }
            }
    
        },
        computed: {
            startDate: function() {
                var date = new Date(this.projectToPut.startDate)
                return (
                    date.getFullYear() +
                    '-' +
                    (date.getMonth() < 9 ?
                        '0' + (date.getMonth() + 1) :
                        date.getMonth() + 1) +
                    '-' +
                    (date.getDate() < 10 ? '0' + date.getDate() : date.getDate())
                )
            },
            endDate: function() {
                var date = new Date(this.projectToPut.endDate)
                return (
                    date.getFullYear() +
                    '-' +
                    (date.getMonth() < 9 ?
                        '0' + (date.getMonth() + 1) :
                        date.getMonth() + 1) +
                    '-' +
                    (date.getDate() < 10 ? '0' + date.getDate() : date.getDate())
                )
            }
        },
        methods: {
            deleteProject: function() {
                if (window.confirm('你确定要删除此项目吗？')) {
                    this.$api.delete(
                        this.$apiUrl +
                        '/projects/' +
                        this.project.id + "?token=" + this.token,
                        null,
                        data => {
                            if (data.responseCode === 401) {
                                window.localStorage.setItem('toLogin', this.$route.path)
                                window.localStorage.removeItem('token')
                                window.localStorage.removeItem('userInfo')
                                this.$router.push({
                                    path: '/login',
                                    replace: true
                                })
                            } else {
                                if (data.successful) {
                                    this.$router.push('/projects')
                                } else {
                                    window.alert(data.information)
                                }
                                window.alert(data.information)
                            }
                        })
                }
    
            },
            removeParticipant: function(staffId, name) {
                if (window.confirm(`你确定要从参与者中移除 ${name} 吗？`)) {
                    this.$api.delete(
                        this.$apiUrl +
                        '/projects/' +
                        this.$route.params.id +
                        '/participants?sId=' +
                        staffId + "&token=" + this.token,
                        null,
                        data => {
                            if (data.responseCode === 401) {
                                window.localStorage.setItem('toLogin', this.$route.path)
                                window.localStorage.removeItem('token')
                                window.localStorage.removeItem('userInfo')
                                this.$router.push({
                                    path: '/login',
                                    replace: true
                                })
                            } else {
                                if (data.successful) {
                                    this.project = data.object
                                }
                                window.alert(data.information)
                            }
    
                        }
                    )
                }
            },
            modifyManager: function() {
                var projectToPut = {}
                projectToPut.managerID = $('.dropdown.edit_manger').dropdown(
                    'get value')
                projectToPut.id = this.project.id
                if (!projectToPut.managerID) {
                    window.alert('请指定新的负责人')
                    return false
                }
                this.$api.put(
                    this.$apiUrl + '/projects/' + this.project.id + '/manager?token=' + this.token,
                    projectToPut,
                    data => {
                        if (data.responseCode === 401) {
                            window.localStorage.setItem('toLogin', this.$route.path)
                            window.localStorage.removeItem('token')
                            window.localStorage.removeItem('userInfo')
                            this.$router.push({
                                path: '/login',
                                replace: true
                            })
                        } else {
                            if (data.successful) {
                                this.project = data.project
                            }
                            window.alert(data.information)
                        }
                    }
                )
            },
            addParticipant: function() {
                var participant = {}
                participant.projectId = this.project.id
                participant.staffId = $('.dropdown.add_participant').dropdown(
                    'get value')
                if (!participant.staffId) {
                    window.alert('请选择需要添加的参与者')
                    return false
                }
                this.$api.post(
                    this.$apiUrl + '/projects/' + this.project.id + '/participants?token=' + this.token,
                    participant,
                    data => {
                        if (data.responseCode === 401) {
                            window.localStorage.setItem('toLogin', this.$route.path)
                            window.localStorage.removeItem('token')
                            window.localStorage.removeItem('userInfo')
                            this.$router.push({
                                path: '/login',
                                replace: true
                            })
                        } else {
                            if (data.successful) {
                                this.project = data.object
                            }
                            window.alert(data.information)
                        }
                    }
                )
            },
            save: function() {
                this.projectToPut.startDate = this.$refs.projectStartDate.value
                this.projectToPut.endDate = this.$refs.projectEndDate.value
                if (!this.projectToPut.name || !this.projectToPut.name.trim()) {
                    window.alert('请填写名称');
                    return false;
                }
                if (!this.projectToPut.startDate) {
                    window.alert('请填写开始时间');
                    return false;
                }
                if (!this.projectToPut.endDate) {
                    window.alert('请填写项目结束时间');
                    return false;
                }
                if (this.projectToPut.startDate > this.projectToPut.endDate) {
                    window.alert('结束时间不得早于开始时间');
                    return false;
                }
                this.$api.put(
                    this.$apiUrl + '/projects/' + this.project.id + '?token=' + this.token,
                    this.projectToPut,
                    data => {
                        if (data.responseCode === 401) {
                            window.localStorage.setItem('toLogin', this.$route.path)
                            window.localStorage.removeItem('token')
                            window.localStorage.removeItem('userInfo')
                            this.$router.push({
                                path: '/login',
                                replace: true
                            })
                        } else {
                            if (data.successful) {
                                this.project = data.object
                                this.projectToPut = {}
                            }
                            window.alert(data.information)
                        }
                    }
                )
                return true
            },
            editProjectEvent: function() {
                this.projectToPut.id = this.project.id
                this.projectToPut.name = this.project.name
                this.projectToPut.description = this.project.description
                this.projectToPut.url = this.project.url
                this.projectToPut.startDate = this.project.startDate
                this.projectToPut.endDate = this.project.endDate
                $('.editProject')
                    .modal({
                        centered: true,
                        blurring: false,
                        inverted: false,
                        closable: false,
                        context: 'div2',
                        onApprove: () => {
                            return this.save()
                        }
                    })
                    .modal('show')
            },
            putProject: function(payload) {
                console.log(payload)
            },
            setRed: function(key) {
                this.redMap[key] = 1
                return true
            },
            getDateList: function() {
                var gapDays = Math.ceil(
                    (new Date(this.project.endDate).getTime() -
                        new Date(this.project.startDate).getTime()) /
                    86400000
                )
                var gapDayBall = Math.ceil(gapDays / (this.dateNum - 1))
                for (var i = 0; i < this.dateNum - 1; i++) {
                    this.dateList.push(
                        new Date(
                            new Date(this.project.startDate).getTime() +
                            gapDayBall * i * 86400000
                        )
                    )
                }
                this.dateList.push(new Date(this.project.endDate))
            },
            refreshProgressBar: function() {
                for (var i = 0; i < this.phases.length; i++) {
                    for (var j = 0; j < this.phases[i].subtaskList.length; j++) {
                        $('#pro' + this.phases[i].subtaskList[j].id).progress({
                            percent: this.phases[i].subtaskList[j].completed
                        })
                    }
                }
            },
            refreshPhases: function() {
                this.$api.get(
                    this.$apiUrl +
                    '/projects/' +
                    this.$route.params.id +
                    '/phases?staffid=' +
                    this.userId + '&token=' + this.token,
                    null,
                    data => {
                        if (data.responseCode === 401) {
                            window.localStorage.setItem('toLogin', this.$route.path)
                            window.localStorage.removeItem('token')
                            window.localStorage.removeItem('userInfo')
                            this.$router.push({
                                path: '/login',
                                replace: true
                            })
                        } else {
                            if (data.successful) {
                                this.phases = data.phaseList
                                if (this.phases != null) {
                                    setTimeout(() => {
                                        this.refreshProgressBar()
                                    }, 20)
                                }
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
            gap: function*() {
                var opeDate = this.startDate
                while (this.opeDate < this.endDate) {
                    yield this.opeDate
                    opeDate.setDate(opeDate.getDate() + 1)
                }
            },
            dates: function() {
                var opeDate = this.startDate
                var datesRet = []
                while (opeDate < this.endDate) {
                    datesRet.push(opeDate)
                    opeDate = new Date(opeDate.setDate(opeDate.getDate() + this.iterval))
                }
                return datesRet
            },
            newPhase: function() {
                this.phase.managerID = $('.dropdown.phase_manger').dropdown('get value')
                if (!this.phase.name) {
                    window.alert('请填写阶段名称')
                    return false
                }
                if (!this.phase.managerID) {
                    window.alert('请选择负责人')
                    return false
                }
                if (!this.phase.startDate) {
                    window.alert('请填写开始时间');
                    return false;
                }
                if (new Date(this.phase.startDate) < new Date(this.project.startDate)) {
                    window.alert(`阶段任务开始时间不得早于项目开始时间，项目开始时间为: ${this.$utils.formatDate(this.project.startDate)}`);
                    return false;
                }
                if (!this.phase.endDate) {
                    window.alert('请填写项目结束时间');
                    return false;
                }
                if (new Date(this.phase.endDate) > new Date(this.project.endDate)) {
                    window.alert(`阶段任务结束不得晚于项目结束时间,项目结束时间为: ${this.$utils.formatDate(this.project.endDate)}`);
                    return false;
                }
                if (this.phase.startDate > this.phase.endDate) {
                    window.alert('结束时间不得早于开始时间');
                    return false;
                }
                this.$api.post(
                    this.$apiUrl +
                    '/projects/' +
                    this.$route.params.id +
                    '/phases?token=' + this.token,
                    this.phase,
                    data => {
                        if (data.responseCode === 401) {
                            window.localStorage.setItem('toLogin', this.$route.path)
                            window.localStorage.removeItem('token')
                            window.localStorage.removeItem('userInfo')
                            this.$router.push({
                                path: '/login',
                                replace: true
                            })
                        } else {
                            if (data.successful) {
                                this.refreshPhases()
                            }
                            window.alert(data.information)
                        }
                    }
                )
            },
            addSubtask: function() {
                this.subtask.managerID = $('.dropdown.subtask_manger').dropdown(
                    'get value'
                )
                this.subtask.assignedID = $('.dropdown.subtask_assign').dropdown(
                    'get value'
                )
                this.subtask.phaseID = $('.dropdown.subtask_phase').dropdown('get value')
                if (this.subtask.phaseID === '') this.subtask.phaseID = this.sub_phaseId
    
                if (!this.subtask.name) {
                    window.alert('请填写阶段名称')
                    return false
                }
                if (!this.subtask.managerID) {
                    window.alert('请选择负责人')
                    return false
                }
                if (!this.subtask.assignedID) {
                    window.alert('请选择分配于谁')
                    return false
                }
                if (!this.subtask.startDate) {
                    window.alert('请填写开始时间');
                    return false;
                }
                let _phase = this.phases.find(_ => _.id = this.subtask.phaseID)
                if (new Date(_phase.startDate) > new Date(this.subtask.startDate)) {
                    window.alert('子任务的开始时间不得早于所属阶段任务的开始时间，当前所属阶段任务的开始时间为：' + this.$utils.formatDate(_phase.startDate));
                    return false;
                }
                if (!this.subtask.endDate) {
                    window.alert('请填写项目结束时间');
                    return false;
                }
                if (new Date(_phase.endDate) < new Date(this.subtask.endDate)) {
                    window.alert('子任务的结束时间不得晚于所属阶段任务的结束时间，当前所属阶段任务的结束时间为：' + this.$utils.formatDate(_phase.endDate));
                    return false;
                }
                if (this.subtask.startDate > this.subtask.endDate) {
                    window.alert('结束时间不得早于开始时间');
                    return false;
                }
    
                this.$api.post(
                    this.$apiUrl +
                    '/projects/' +
                    this.$route.params.id +
                    '/subtasks?token=' + this.token,
                    this.subtask,
                    data => {
                        if (data.responseCode === 401) {
                            window.localStorage.setItem('toLogin', this.$route.path)
                            window.localStorage.removeItem('token')
                            window.localStorage.removeItem('userInfo')
                            this.$router.push({
                                path: '/login',
                                replace: true
                            })
                        } else {
                            if (data.successful) {
                                this.refreshPhases()
                            }
                            window.alert(data.information)
                        }
                    }
                )
                return true
            },
            newPhaseEvent: function() {
                $('.phase')
                    .modal({
                        centered: true,
                        blurring: false,
                        inverted: false,
                        closable: false,
                        context: 'div2',
                        onApprove: () => {
                            return this.newPhase()
                        }
                    })
                    .modal('show')
            },
            newSubtaskEvent: function(phaseId, subPhaseName) {
                this.sub_phaseId = phaseId
                this.phaseName = subPhaseName
                $('.subtask')
                    .modal({
                        centered: true,
                        blurring: false,
                        inverted: false,
                        closable: false,
                        context: 'div2',
                        onApprove: () => {
                            return this.addSubtask()
                        }
                    })
                    .modal('show')
            },
            showDropdownLocal: function(className) {
                $('.' + className).dropdown()
            },
            showDropdown: function(className, maxSelections) {
                $('.' + className).dropdown({
                    apiSettings: {
                        url: this.$apiUrl + '/staff?name={query}'
                    },
                    maxSelections: maxSelections
                })
            },
            intervalSet: function() {
                $('.ui.dropdown.button').dropdown({
                    allowCategorySelection: true
                })
            },
            progress: function(id, destination) {
                $('#' + id).progress({
                    percent: destination
                })
            },
            formatDate: function(date) {
                date = new Date(date)
                return (
                    date.getFullYear() +
                    ' 年 ' +
                    (date.getMonth() + 1) +
                    ' 月 ' +
                    date.getDate() +
                    ' 日 '
                )
            }
        },
        mounted() {
            this.sq = this.gap()
            this.dateNum = parseInt(
                (this.$refs.temporalPlanning.getBoundingClientRect().width - 19.6) / 42.03
            )
            $('.staffDropdown').dropdown({
                apiSettings: {
                    url: this.$apiUrl + '/staff?name={query}&token=' + this.token,
                    // onResponse: res => {
                    //     for (let i = 0; i < res.results.length; i++) {
                    //         if (this.staffExcept.find(_ => _ === res.results[i].id)) {
                    //             res.results.splice(i, 1)
                    //             i--
                    //         }
                    //     }
                    //     return resp
                    // },
                },
                maxSelections: 1
            })
            $('.phaseDropdown').dropdown()
            $('.popupTriger').popup({
                inline: true,
                hoverable: true,
                position: 'right center',
                delay: {
                    show: 0,
                    hide: 500
                },
                closable: false,
                on: 'click'
            })
        }
    }
</script>

<style scoped>
    /* .scrollY {
                                                                                                    overflow-x: scroll;
                                                                                                    overflow-y: hidden;
                                                                                                    } */
    
    .link {
        color: rgba(0, 0, 0, 0.7);
    }
    
    .link:hover {
        color: black;
        cursor: pointer;
        cursor: hand;
    }
    
    .complete {
        width: 500px;
    }
    
    .ui.progress:last-child {
        margin: 0px;
    }
</style>
