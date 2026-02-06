<template>
  <div class="message-board-container">
    <div class="message-header">
      <h1>留言板</h1>
      <p>欢迎留下您的宝贵意见或建议！</p>
    </div>

    <div class="message-form-container">
      <el-card class="message-form-card">
        <div slot="header" class="clearfix">
          <span>发表留言</span>
        </div>
        <el-form :model="messageForm" :rules="messageRules" ref="messageForm" label-width="80px">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="姓名" prop="userName">
                <el-input v-model="messageForm.userName" placeholder="请输入您的姓名"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="联系方式" prop="contactInfo">
                <el-input v-model="messageForm.contactInfo" placeholder="请输入您的联系方式"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="留言内容" prop="messageContent">
            <el-input 
              type="textarea" 
              :rows="4"
              placeholder="请输入您的留言内容..." 
              v-model="messageForm.messageContent">
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button 
              type="primary" 
              @click="submitMessage" 
              :loading="submitting"
              :disabled="submitting">
              提交留言
            </el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>

    <div class="message-list-container">
      <el-card>
        <div slot="header" class="clearfix">
          <span>最新留言</span>
          <el-button 
            style="float: right; padding: 3px 0" 
            type="text" 
            icon="el-icon-refresh" 
            @click="loadMessages">
            刷新
          </el-button>
        </div>
        <div v-if="messages.length === 0" class="no-messages">
          暂无留言，快来抢沙发吧！
        </div>
        <div v-else>
          <div v-for="msg in messages" :key="msg.messageId" class="message-item">
            <div class="message-user-info">
              <strong>{{ msg.userName || '匿名用户' }}</strong>
              <span class="message-time">{{ formatDate(msg.createTime) }}</span>
            </div>
            <div class="message-content">{{ msg.messageContent }}</div>
            <div v-if="msg.contactInfo" class="message-contact">
              联系方式: {{ msg.contactInfo }}
            </div>
          </div>
          
          <div class="load-more">
            <el-button 
              v-if="hasMore" 
              type="primary" 
              plain 
              @click="loadMore">
              加载更多
            </el-button>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import { addPublicMessage, listMessage } from "@/api/system/message";

export default {
  name: "PublicMessageBoard",
  data() {
    return {
      messageForm: {
        userName: '',
        contactInfo: '',
        messageContent: ''
      },
      messageRules: {
        userName: [
          { required: true, message: '请输入您的姓名', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        messageContent: [
          { required: true, message: '请输入留言内容', trigger: 'blur' },
          { min: 5, max: 500, message: '长度在 5 到 500 个字符', trigger: 'blur' }
        ]
      },
      messages: [],
      submitting: false,
      currentPage: 1,
      pageSize: 10,
      hasMore: true
    }
  },
  mounted() {
    this.loadMessages();
  },
  methods: {
    submitMessage() {
      this.$refs.messageForm.validate((valid) => {
        if (valid) {
          this.submitting = true;
          
          // 准备提交的数据
          const submitData = {
            userName: this.messageForm.userName,
            contactInfo: this.messageForm.contactInfo,
            messageContent: this.messageForm.messageContent
          };
          
          addPublicMessage(submitData)
            .then(response => {
              this.$message.success('留言提交成功！');
              this.resetForm();
              // 重新加载留言列表
              this.currentPage = 1;
              this.messages = [];
              this.loadMessages();
            })
            .catch(error => {
              this.$message.error('留言提交失败：' + error.msg);
            })
            .finally(() => {
              this.submitting = false;
            });
        } else {
          console.log('表单验证失败!');
          return false;
        }
      });
    },
    
    resetForm() {
      this.messageForm = {
        userName: '',
        contactInfo: '',
        messageContent: ''
      };
      this.$refs.messageForm.clearValidate();
    },
    
    loadMessages() {
      const params = {
        pageNum: this.currentPage,
        pageSize: this.pageSize,
        status: '0' // 只显示正常状态的留言
      };
      
      listMessage(params)
        .then(response => {
          if (response.code === 200) {
            if (this.currentPage === 1) {
              this.messages = response.rows;
            } else {
              this.messages = this.messages.concat(response.rows);
            }
            
            // 检查是否还有更多数据
            this.hasMore = response.rows.length === this.pageSize;
          }
        })
        .catch(error => {
          this.$message.error('获取留言列表失败：' + error.msg);
        });
    },
    
    loadMore() {
      this.currentPage++;
      this.loadMessages();
    },
    
    formatDate(dateString) {
      if (!dateString) return '';
      
      const date = new Date(dateString);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      
      return `${year}-${month}-${day} ${hours}:${minutes}`;
    }
  }
}
</script>

<style scoped>
.message-board-container {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f5f5f5;
  min-height: 100vh;
}

.message-header {
  text-align: center;
  margin-bottom: 30px;
  padding: 20px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.message-header h1 {
  color: #303133;
  margin: 0 0 10px 0;
}

.message-header p {
  color: #909399;
  margin: 0;
}

.message-form-container {
  margin-bottom: 30px;
}

.message-form-card {
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.message-list-container {
  margin-bottom: 20px;
}

.message-list-container .el-card {
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.no-messages {
  text-align: center;
  color: #909399;
  font-size: 16px;
  padding: 40px 0;
}

.message-item {
  padding: 15px;
  border-bottom: 1px solid #ebeef5;
  background-color: white;
  margin-bottom: 10px;
  border-radius: 4px;
}

.message-item:last-child {
  border-bottom: none;
}

.message-user-info {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  font-size: 14px;
}

.message-time {
  color: #909399;
  font-size: 12px;
}

.message-content {
  color: #606266;
  line-height: 1.6;
  margin-bottom: 8px;
}

.message-contact {
  font-size: 12px;
  color: #909399;
  border-top: 1px dashed #e4e7ed;
  padding-top: 8px;
}

.load-more {
  text-align: center;
  margin-top: 20px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
</style>