<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="问题类型" prop="questionType">
        <el-select v-model="queryParams.questionType" placeholder="请选择问题类型" clearable size="small">
          <el-option
            v-for="dict in questionTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          v-hasPermi="['item:question:add']"
        >新增
          <!--@click="handleAdd"-->
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['item:question:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['item:question:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['item:question:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="questionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="序号" width="70px">
        <template slot-scope="scope">
          {{scope.$index+1}}
        </template>
      </el-table-column>
      <el-table-column label="课程" align="center" prop="jsSubject.name" width="120px"/>
      <el-table-column label="题型" align="center" prop="questionType" width="70px" :formatter="questionTypeFormat"/>
      <el-table-column label="题干" align="center" prop="shortTitle" show-overflow-tooltip/>
      <el-table-column label="总分" align="center" prop="score" width="60px"/>
      <el-table-column label="难度" align="center" prop="difficult" width="60px"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="220px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="showQuestion(scope.row)"
          >预览
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="editQuestion(scope.row)"
            v-hasPermi="['item:question:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['item:question:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :visible.sync="questionShow.dialog" style="width: 100%;height: 100%">
      <QuestionShow :qType="questionShow.qType" :question="questionShow.question" :qLoading="questionShow.loading"/>
    </el-dialog>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改题目对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="问题类型" prop="questionType">
          <el-select v-model="form.questionType" placeholder="请选择问题类型">
            <el-option
              v-for="dict in questionTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="课程" prop="subjectId">
          <el-select v-model="form.subjectId" placeholder="请选择课程">
            <el-option
              v-for="dict in subjectOptions"
              :key="dict.id"
              :label="dict.name"
              :value="parseInt(dict.id)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="题干总分" prop="score">
          <el-input v-model="form.score" placeholder="请输入题干总分"/>
        </el-form-item>
        <el-form-item label="题目难度" prop="difficult">
          <el-input v-model="form.difficult" placeholder="请输入题目难度"/>
        </el-form-item>
        <el-form-item label="正确答案" prop="correct">
          <el-input v-model="form.correct" placeholder="请输入正确答案"/>
        </el-form-item>
        <el-form-item label="题目" prop="textContentId">
          <el-input v-model="form.textContentId" placeholder="请输入题目"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapGetters, mapState, mapActions} from 'vuex'
  import {
    listQuestion,
    getQuestion,
    delQuestion,
    addQuestion,
    updateQuestion,
    exportQuestion
  } from "@/api/item/question";
  import QuestionShow from './components/Show';
  import Pagination from '@/components/Pagination'
  import {listSubject} from "@/api/item/subject";

  export default {
    name: "Question",
    components: {Pagination, QuestionShow},
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 题目表格数据
        questionList: [],
        // 课程数据
        subjectOptions: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 问题类型字典
        questionTypeOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          questionType: null,
          difficult: null,
          status: null
        },
        questionShow: {
          qType: 0,
          dialog: false,
          question: null,
          loading: false
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {}
      };
    },
    created() {
      this.getList();
      this.getDicts("question_type").then(response => {
        this.questionTypeOptions = response.data;
      });
      listSubject().then(response => {
        this.subjectOptions = response.rows;
      });
    },
    methods: {
      /** 查询题目列表 */
      getList() {
        this.loading = true;
        listQuestion(this.queryParams).then(response => {
          this.questionList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 问题类型字典翻译
      questionTypeFormat(row, column) {
        return this.selectDictLabel(this.questionTypeOptions, row.questionType);
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          questionType: null,
          subjectId: null,
          score: null,
          difficult: null,
          correct: null,
          textContentId: null,
          createTime: null,
          createBy: null,
          updateTime: null,
          updateBy: null,
          delFlag: null,
          status: "0"
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      showQuestion(row) {
        let _this = this
        this.questionShow.dialog = true
        this.questionShow.loading = true
        getQuestion(row.id).then(re => {
          _this.questionShow.qType = re.data.questionType
          _this.questionShow.question = re.data
          _this.questionShow.loading = false
        })
      },
      editQuestion(row) {
        let url = this.enumFormat(this.editUrlEnum, row.questionType)
        this.$router.push({path: url, query: {id: row.id}})
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加题目";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getQuestion(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改题目";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateQuestion(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addQuestion(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$confirm('是否确认删除题目编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delQuestion(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有题目数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return exportQuestion(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
    },
    computed: {
      ...mapGetters('enumItem', ['enumFormat']),
      ...mapState('enumItem', {
        questionType: state => state.exam.question.typeEnum,
        editUrlEnum: state => state.exam.question.editUrlEnum,
        levelEnum: state => state.user.levelEnum
      }),
      ...mapGetters('exam', ['subjectEnumFormat']),
      ...mapState('exam', {subjects: state => state.subjects})
    }
  };
</script>
