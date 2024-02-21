<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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
          v-hasPermi="['wms:consumestatis:add']"
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
          v-hasPermi="['wms:consumestatis:edit']"
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
          v-hasPermi="['wms:consumestatis:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['wms:consumestatis:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="consumestatisList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="consumptionStatisId" />
      <el-table-column label="商品名" align="center" prop="goodsName" />
      <el-table-column label="规格型号" align="center" prop="normsModel" />
      <el-table-column label="消耗量" align="center" prop="consumption" />
      <el-table-column label="消耗时间长度" align="center" prop="time" />
      <el-table-column label="消耗率下限" align="center" prop="lowerLimit" />
      <el-table-column label="消耗率上限" align="center" prop="upperLimit" />
      <el-table-column label="预警状态" align="center" prop="state" />
      <el-table-column label="异常原因" align="center" prop="reason" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['wms:consumestatis:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['wms:consumestatis:remove']"
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

    <!-- 添加或修改商品消耗统计对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="消耗量" prop="consumption">
          <el-input v-model="form.consumption" placeholder="请输入消耗量" />
        </el-form-item>
        <el-form-item label="消耗时间长度" prop="time">
          <el-input v-model="form.time" placeholder="请输入消耗时间长度" />
        </el-form-item>
        <el-form-item label="消耗率下限" prop="lowerLimit">
          <el-input v-model="form.lowerLimit" placeholder="请输入消耗率下限" />
        </el-form-item>
        <el-form-item label="消耗率上限" prop="upperLimit">
          <el-input v-model="form.upperLimit" placeholder="请输入消耗率上限" />
        </el-form-item>
        <el-form-item label="异常原因" prop="reason">
          <el-input v-model="form.reason" placeholder="请输入异常原因" />
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
import { listConsumestatis, getConsumestatis, delConsumestatis, addConsumestatis, updateConsumestatis } from "@/api/wms/consumestatis";

export default {
  name: "Consumestatis",
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
      // 商品消耗统计表格数据
      consumestatisList: [],
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
        state: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        goodsId: [
          { required: true, message: "商品ID不能为空", trigger: "change" }
        ],
        goodsName: [
          { required: true, message: "商品名不能为空", trigger: "change" }
        ],
        normsModel: [
          { required: true, message: "规格型号不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询商品消耗统计列表 */
    getList() {
      this.loading = true;
      listConsumestatis(this.queryParams).then(response => {
        this.consumestatisList = response.rows;
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
        consumptionStatisId: null,
        goodsId: null,
        goodsName: null,
        normsModel: null,
        consumption: null,
        time: null,
        lowerLimit: null,
        upperLimit: null,
        state: null,
        reason: null
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
      this.ids = selection.map(item => item.consumptionStatisId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品消耗统计";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const consumptionStatisId = row.consumptionStatisId || this.ids
      getConsumestatis(consumptionStatisId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品消耗统计";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.consumptionStatisId != null) {
            updateConsumestatis(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addConsumestatis(this.form).then(response => {
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
      const consumptionStatisIds = row.consumptionStatisId || this.ids;
      this.$modal.confirm('是否确认删除商品消耗统计编号为"' + consumptionStatisIds + '"的数据项？').then(function() {
        return delConsumestatis(consumptionStatisIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('wms/consumestatis/export', {
        ...this.queryParams
      }, `consumestatis_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
