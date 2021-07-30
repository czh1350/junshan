<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="试卷名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入试卷名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="试卷类型" prop="paperType">
        <el-select v-model="queryParams.paperType" placeholder="请选择试卷类型" clearable size="small">
          <el-option
            v-for="dict in paperTypeOptions"
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
          @click="handleAdd"
          v-hasPermi="['item:exam:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['item:exam:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['item:exam:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['item:exam:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="examList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="试卷名称" align="center" prop="name" />
      <el-table-column label="试卷类型" align="center" prop="paperType" :formatter="paperTypeFormat" />
      <el-table-column label="分数" align="center" prop="score" />
      <el-table-column label="试题数量" align="center" prop="questionCount" />
      <el-table-column label="建议时长(分钟)" align="center" prop="suggestTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['item:exam:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['item:exam:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改试卷对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="试卷名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入试卷名称" />
        </el-form-item>
        <el-form-item label="课程id" prop="subjectId">
          <el-input v-model="form.subjectId" placeholder="请输入课程id" />
        </el-form-item>
        <el-form-item label="试卷类型" prop="paperType">
          <el-select v-model="form.paperType" placeholder="请选择试卷类型">
            <el-option
              v-for="dict in paperTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分数" prop="score">
          <el-input v-model="form.score" placeholder="请输入分数" />
        </el-form-item>
        <el-form-item label="试题数量" prop="questionCount">
          <el-input v-model="form.questionCount" placeholder="请输入试题数量" />
        </el-form-item>
        <el-form-item label="建议时长(分钟)" prop="suggestTime">
          <el-input v-model="form.suggestTime" placeholder="请输入建议时长(分钟)" />
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
import { listExam, getExam, delExam, addExam, updateExam, exportExam } from "@/api/item/exam";

export default {
  name: "Exam",
  components: {
  },
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
      // 试卷表格数据
      examList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 试卷类型字典
      paperTypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        paperType: null,
        suggestTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("paper_type").then(response => {
      this.paperTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询试卷列表 */
    getList() {
      this.loading = true;
      listExam(this.queryParams).then(response => {
        this.examList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 试卷类型字典翻译
    paperTypeFormat(row, column) {
      return this.selectDictLabel(this.paperTypeOptions, row.paperType);
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
        name: null,
        subjectId: null,
        paperType: null,
        score: null,
        questionCount: null,
        suggestTime: null,
        textQuestionId: null,
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null,
        delFlag: null
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加试卷";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getExam(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改试卷";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateExam(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExam(this.form).then(response => {
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
      this.$confirm('是否确认删除试卷编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delExam(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有试卷数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportExam(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
