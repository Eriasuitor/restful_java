<template>
    <div2>
        <h4 class="ui horizontal divider header" v-if="projects.find(_ => _.managerID === userInfo.id)">
            <i class="briefcase icon"></i> 我管理的
        </h4>
        <div class="ui link cards">
            <div class="card" @click="detail(project.id)" v-for="project in projects.filter(_ => _.managerID === userInfo.id)" :key="project.id">
                <div class="content">
                    <div class="header">{{project.name}}</div>
                    <div class="meta">
                        {{project.status}}
                    </div>
                    <div class="description ellipsis">{{project.description}}</div>
                </div>
                <div class="extra content">
                    <span class="right floated">{{Math.round(project.completed / project.requiredTime * 100) || 0}}% 完成度</span>
                    <!-- <span><i class="user icon"></i> {{project.participants}} 参与者</span> -->
                </div>
            </div>
        </div>
    
        <h4 class="ui horizontal divider header" v-if="projects.find(_ => _.managerID != userInfo.id)">
            <i class="users icon"></i> 我参与的
        </h4>
        <div class="ui link cards">
            <div class="card" @click="detail(project.id)" v-for="project in projects.filter(_ => _.managerID != userInfo.id)" :key="project.id">
                <div class="content">
                    <div class="header">{{project.name}}</div>
                    <div class="meta">
                        {{project.status}}
                    </div>
                    <div class="description ellipsis">{{project.description}}</div>
                </div>
                <div class="extra content">
                    <span class="right floated"> {{Math.round(project.completed / project.requiredTime * 100) || 0}}% 完成度 </span>
                    <!-- <span><i class="user icon"></i> {{project.participants}} 参与者 </span> -->
                </div>
            </div>
        </div>

         <div class="ui horizontal divider header" v-if="projects.length === 0">暂无历史项目</div>
    </div2>
</template>

<script>
    export default {
        data() {
            return {
                projects: [],
                userInfo: {},
            }
        },
        created() {
            if (!window.localStorage.getItem('token') || !window.localStorage.getItem('userInfo')) {
                window.localStorage.removeItem('token')
                window.localStorage.removeItem('userInfo')
                window.localStorage.setItem('toLogin', '/projects')
                this.$router.push({
                    path: '/login',
                    replace: true
                })
            } else {
                this.userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
                this.refreshProjects()
            }
        },
        methods: {
            refreshProjects: function() {
                this.$api.get(
                    this.$apiUrl + '/history?token=' + window.localStorage.getItem('token'),
                    null,
                    data => {
                        if (data.responseCode === 401) {
                            window.localStorage.setItem('toLogin', '/projects')
                            window.localStorage.removeItem('token')
                            window.localStorage.removeItem('userInfo')
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
            detail: function(pId) {
                this.$router.push({
                    path: '/projects/' + pId,
                    replace: true
                })
            }
        },
        mounted() {
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
