<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品名" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规格型号" prop="normsModel">
        <el-input
          v-model="queryParams.normsModel"
          placeholder="请输入规格型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="领用人" prop="recipient">
        <el-input
          v-model="queryParams.recipient"
          placeholder="请输入领用人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="部门" prop="department">
        <el-input
          v-model="queryParams.department"
          placeholder="请输入部门"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
      <el-form-item label="领用时间" prop="receiveTime">
        <el-date-picker clearable
          v-model="queryParams.receiveTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择领用时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="审批人" prop="approver">
        <el-input
          v-model="queryParams.approver"
          placeholder="请输入审批人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交接办理人" prop="handover">
        <el-input
          v-model="queryParams.handover"
          placeholder="请输入交接办理人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否固定资产" prop="receiveType">
        <el-select v-model="queryParams.receiveType" placeholder="请选择" clearable>
          <el-option
            v-for="dict in receiveTypeOptions"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
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
          v-hasPermi="['receive:receiveRecord:add']"
        >新增固定资产领用单</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['receive:receiveRecord:edit']"
        >修改</el-button>
      </el-col>
<!--      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['receive:receiveRecord:remove']"
        >删除</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['receive:receiveRecord:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="receiveRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="id" v-if="false"/>
      <el-table-column label="商品ID" align="center" prop="goodsId" v-if="false" />
      <el-table-column label="商品名" align="center" prop="goodsName" />
      <el-table-column label="规格型号" align="center" prop="normsModel" />
      <el-table-column label="领取数量" align="center" prop="quantity" />
      <el-table-column label="领用人" align="center" prop="recipient" />
      <el-table-column label="部门" align="center" prop="department" />
      <el-table-column label="领用时间" align="center" prop="receiveTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.receiveTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用途" align="center" prop="useTo" />
      <el-table-column label="审批人" align="center" prop="approver" />
      <el-table-column label="交接办理人" align="center" prop="handover" />
      <el-table-column label="备注" align="center" prop="remark">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.remark" placement="top">
            <div>{{ scope.row.remark==null? "":scope.row.remark.substring(0, 5) }}</div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="是否固定资产" align="center" prop="receiveType" v-if="false"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['receive:receiveRecord:edit']"
          >修改</el-button>

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

    <!-- 添加或修改领用记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="规格型号" prop="normsModel">
          <el-input v-model="form.normsModel" placeholder="请输入规格型号" />
        </el-form-item>
        <el-form-item label="领取数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入领取数量" />
        </el-form-item>
        <el-form-item label="领用人" prop="recipient">
          <el-input v-model="form.recipient" placeholder="请输入领用人" />
        </el-form-item>
        <el-form-item label="部门" prop="department">
          <el-input v-model="form.department" placeholder="请输入部门" />
        </el-form-item>
        <el-form-item label="领用时间" prop="receiveTime">
          <el-date-picker clearable
            v-model="form.receiveTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择领用时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="用途" prop="useTo">
          <el-input v-model="form.useTo" placeholder="请输入用途" />
        </el-form-item>
        <el-form-item label="审批人" prop="approver">
          <el-input v-model="form.approver" placeholder="请输入审批人" />
        </el-form-item>
        <el-form-item label="交接办理人" prop="handover">
          <el-input v-model="form.handover" placeholder="请输入交接办理人" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="是否固定资产" prop="remark">
          <el-select v-model="form.receiveType" placeholder="请选择">
            <el-option
              v-for="dict in receiveTypeOptions"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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
import { listReceiveRecord, getReceiveRecord, delReceiveRecord, addReceiveRecord, updateReceiveRecord } from "@/api/wms/receiveRecord";

export default {
  name: "ReceiveRecord",
  data() {
    return {
      // 遮罩层
      loading: true,
      receiveTypeOptions: [{value:"1",label:"固定资产"},{value:"2",label:"非固定资产"}],
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
      // 领用记录表格数据
      receiveRecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsName: null,
        normsModel: null,
        recipient: null,
        department: null,
        receiveTime: null,
        approver: null,
        handover: null,
        receiveType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {

        goodsName: [
          { required: true, message: "商品名不能为空", trigger: "change" }
        ],
        quantity: [
          { required: true, message: "领取数量不能为空", trigger: "blur" }
        ],
        recipient: [
          { required: true, message: "领用人不能为空", trigger: "blur" }
        ],
        receiveTime: [
          { required: true, message: "领用时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询领用记录列表 */
    getList() {
      this.loading = true;
      listReceiveRecord(this.queryParams).then(response => {
        this.receiveRecordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        goodsId: null,
        goodsName: null,
        normsModel: null,
        quantity: null,
        recipient: null,
        department: null,
        receiveTime: null,
        useTo: null,
        approver: null,
        handover: null,
        remark: null,
        receiveType: null
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
      this.form.receiveTime=new Date()
      this.open = true;
      this.title = "添加领用记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getReceiveRecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改领用记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateReceiveRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.receiveType="1"
            addReceiveRecord(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
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
      this.$modal.confirm('是否确认删除领用记录编号为"' + ids + '"的数据项？').then(function() {
        return delReceiveRecord(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('receive/receiveRecord/export', {
        ...this.queryParams
      }, `receiveRecord_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
