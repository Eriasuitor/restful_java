<template>
    <div2>
        <div class="ui breadcrumb">
            <a class="section">JIRA</a>
            <div class="divider"> / </div>
            <div class="active section">Projects</div>
        </div>
        <div class="ui search">
            <div class="ui left icon input">
                <input class="prompt" type="text" placeholder="项目搜索" @input="search">
                <i class="search icon"></i>
            </div>
            <button class="ui labeled icon button right floated" @click="newProjectEvent"><i class="plus icon"></i>新建项目</button>
        </div>
        <h4 class="ui horizontal divider header" v-if="projects.find(_ => _.managerID === userId)">
            <i class="briefcase icon"></i> 我管理的
        </h4>
        <div class="ui link cards">
            <div class="card" @click="detail(project.id)" v-for="project in projects.filter(_ => _.managerID === userId)" :key="project.id">
                <div class="content">
                    <div class="header">{{project.name}}</div>
                    <div class="meta">
                        {{project.status}}
                    </div>
                    <div class="description ellipsis">{{project.description}}</div>
                </div>
                <div class="extra content">
                    <span class="right floated">{{project.completed}}% 完成度</span>
                    <span><i class="user icon"></i> {{project.participants}} 参与者</span>
                </div>
            </div>
        </div>
    
        <h4 class="ui horizontal divider header" v-if="projects.find(_ => _.managerID != userId)">
            <i class="users icon"></i> 我参与的
        </h4>
        <div class="ui link cards">
            <div class="card" @click="detail(project.id)" v-for="project in projects.filter(_ => _.managerID != userId)" :key="project.id">
                <div class="content">
                    <div class="header">{{project.name}}</div>
                    <div class="meta">
                        {{project.status}}
                    </div>
                    <div class="description ellipsis">{{project.description}}</div>
                </div>
                <div class="extra content">
                    <span class="right floated"> {{project.completed}}% 完成度 </span>
                    <span><i class="user icon"></i> {{project.participants}} 参与者 </span>
                </div>
            </div>
        </div>
        <div class="ui modal newProject" id='newProject'>
            <i class="close icon"></i>
            <div class="header">
                新建项目
            </div>
            <div class="content">
                <form class="ui form">
                    <div class="field required">
                        <label>项目名称</label>
                        <input type="text" v-model="project.name">
                    </div>
                    <div class="field">
                        <label>描述</label>
                        <textarea rows="6" maxlength="800" v-model="project.description"></textarea>
                    </div>
    
                    <div class="fields">
                        <div class="eight wide field required">
                            <label>负责人</label>
                            <div class="ui fluid multiple search selection dropdown manager staffDropdown">
                                <input type="hidden" name="receipt">
                                <i class="dropdown icon"></i>
                                <div class="default text"></div>
                            </div>
                        </div>
                        <div class="eight wide field">
                            <label>代码库地址</label>
                            <input type="url">
                        </div>
                    </div>
                    <div class="fields">
                        <div class="eight wide field required">
                            <label>开始时间</label>
                            <input type="date" v-model="project.startDate">
                        </div>
                        <div class="eight wide field required">
                            <label>结束时间</label>
                            <input type="date" v-model="project.endDate">
                        </div>
                    </div>
                    <div class="field">
                        <label>参与人</label>
                        <div class="fields">
                            <div class="fourteen wide field">
                                <div class="ui fluid multiple search selection dropdown participants staffDropdownComplex">
                                    <input type="hidden" name="receipt">
                                    <i class="dropdown icon"></i>
                                    <div class="default text"></div>
                                </div>
                            </div>
                            <div class="two wide field">
                                <div class="ui button" @click="empty">
                                    清空
                                </div>
                            </div>
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
            <div v-bind:class="{ui:true, dimmer:true, inverted:true, active:isSaving}">
                <div class="ui text loader ">保存中</div>
            </div>
        </div>
    
        <!-- <new-porject-modal :project="project"></new-porject-modal>1 -->
    </div2>
</template>

<script>
    // import NewPorjectModal from '../components/newPorjectModal'
    export default {
        //   components: { NewPorjectModal },
        data() {
            return {
                projects: [],
                userId: 0,
                project: {},
                isSaving: false
            }
        },
        created() {
            if (!window.localStorage.getItem('token') || !window.localStorage.getItem('userId')) {
                window.localStorage.setItem('toLogin', '/projects')
                this.$router.push({
                    path: '/login',
                    replace: true
                })
            } else {
                this.userId = parseInt(window.localStorage.getItem('userId'))
                this.refreshProjects()
            }
        },
        methods: {
            newProject: function() {
                this.project.managerID = $('.dropdown.manager').dropdown('get value')
                this.project.participantsList = []
                if ($('.dropdown.participants')
                    .dropdown('get value') != '')
                    $('.dropdown.participants')
                    .dropdown('get value')
                    .split(',')
                    .forEach(sId => {
                        this.project.participantsList.push({
                            staffId: sId
                        })
                    })
                if (!this.project.name) {
                    window.alert('请填写名称');
                    return false;
                }
                if (!this.project.managerID) {
                    window.alert('请填写负责人');
                    return false;
                }
                if (!this.project.startDate) {
                    window.alert('请填写开始时间');
                    return false;
                }
                if (!this.project.endDate) {
                    window.alert('请填写项目结束时间');
                    return false;
                }
                if (this.project.startDate > this.project.endDate) {
                    window.alert('结束时间不得早于开始时间');
                    return false;
                }
                this.$api.post(
                    this.$apiUrl + '/projects?token=' + window.localStorage.getItem('token'),
                    this.project,
                    data => {
                        if (data.responseCode === 401) {
                            window.localStorage.setItem('toLogin', '/projects')
                            this.$router.push({
                                path: '/login',
                                replace: true
                            })
                        } else {
                            window.alert(data.information)
                            this.refreshProjects()
                        }
                    }
                )
                return true
            },
            refreshProjects: function() {
                this.$api.get(
                    this.$apiUrl + '/projects?token=' + window.localStorage.getItem('token'),
                    null,
                    data => {
                        if (data.responseCode === 401) {
                            window.localStorage.setItem('toLogin', '/projects')
                            this.$router.push({
                                path: '/login',
                                replace: true
                            })
                        } else {
                            if (data.successful)
                                this.projects = data.projectNames
                            else window.alert(data.information)
                        }
                    }
                )
            },
            newProjectEvent: function() {
                $('.newProject')
                    .modal({
                        centered: true,
                        inverted: false,
                        blurring: false,
                        closable: false,
                        context: 'div2',
                        onApprove: () => {
                            return this.newProject()
                        }
                    })
                    .modal('show')
            },
            empty: function() {
                $('.dropdown.participants').dropdown('clear')
            },
            search: function() {
                $('.ui.search').search({
                    apiSettings: {
                        onResponse: resp => {
                            $.each(resp.projectNames, function(index, item) {
                                item.url = '/projects/' + item.id
                            })
                            return resp
                        },
                        url: this.$apiUrl + '/projects?q={query}&token=' + window.localStorage.getItem('token')
                    },
                    fields: {
                        results: 'projectNames',
                        title: 'name',
                        url: 'url'
                    },
                    minCharacters: 1
                })
            },
            detail: function(pId) {
                this.$router.push({
                    path: '/projects/' + pId,
                    replace: true
                })
            }
        },
        mounted() {
            $('.staffDropdown').dropdown({
                apiSettings: {
                    url: this.$apiUrl + '/staff?name={query}',
                },
                maxSelections: 1
            })
            $('.staffDropdownComplex').dropdown({
                apiSettings: {
                    url: this.$apiUrl + '/staff?name={query}'
                }
            })
        }
    }
</script>

<style scoped>
    .breadcrumb {
        margin-bottom: 15px;
    }
    
    .ellipsis {
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
    }
</style>
