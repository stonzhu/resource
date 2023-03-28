<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="交易商品" prop="goodsId">
        <el-select v-model="queryParams.goodsId" placeholder="请选择交易商品" clearable>
          <el-option
            v-for="dict in dict.type.pro_rushsale_buyfrom"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="卖方" prop="dealFrom">
        <el-input
          v-model="queryParams.dealFrom"
          placeholder="请输入卖方"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="买方" prop="dealTo">
        <el-input
          v-model="queryParams.dealTo"
          placeholder="请输入买方"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交易时间" prop="dealTime">
        <el-date-picker clearable
          v-model="queryParams.dealTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择交易时间">
        </el-date-picker>
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
          v-hasPermi="['rushsale:dealinfo:add']"
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
          v-hasPermi="['rushsale:dealinfo:edit']"
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
          v-hasPermi="['rushsale:dealinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['rushsale:dealinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dealinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="交易id" align="center" prop="dealId" />
      <el-table-column label="交易商品" align="center" prop="goodsId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pro_rushsale_buyfrom" :value="scope.row.goodsId"/>
        </template>
      </el-table-column>
      <el-table-column label="卖方" align="center" prop="dealFrom" />
      <el-table-column label="买方" align="center" prop="dealTo" />
      <el-table-column label="交易金额" align="center" prop="dealNum" />
      <el-table-column label="交易类型" align="center" prop="dealType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pro_rush_dealtype" :value="scope.row.dealType"/>
        </template>
      </el-table-column>
      <el-table-column label="交易来源" align="center" prop="handleOrderId" />
      <el-table-column label="交易时间" align="center" prop="dealTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dealTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['rushsale:dealinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['rushsale:dealinfo:remove']"
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

    <!-- 添加或修改抢购交易信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="部门id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入部门id" />
        </el-form-item>
        <el-form-item label="交易商品" prop="goodsId">
          <el-select v-model="form.goodsId" placeholder="请选择交易商品">
            <el-option
              v-for="dict in dict.type.pro_rushsale_buyfrom"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="卖方" prop="dealFrom">
          <el-input v-model="form.dealFrom" placeholder="请输入卖方" />
        </el-form-item>
        <el-form-item label="买方" prop="dealTo">
          <el-input v-model="form.dealTo" placeholder="请输入买方" />
        </el-form-item>
        <el-form-item label="交易金额" prop="dealNum">
          <el-input v-model="form.dealNum" placeholder="请输入交易金额" />
        </el-form-item>
        <el-form-item label="交易类型" prop="dealType">
          <el-select v-model="form.dealType" placeholder="请选择交易类型-buy：我为买方 sale：我为卖方">
            <el-option
              v-for="dict in dict.type.pro_rush_dealtype"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交易时间" prop="dealTime">
          <el-date-picker clearable
            v-model="form.dealTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择交易时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listDealinfo, getDealinfo, delDealinfo, addDealinfo, updateDealinfo } from "@/api/rushsale/dealinfo";

export default {
  name: "Dealinfo",
  dicts: ['pro_rushsale_buyfrom', 'pro_rush_dealtype'],
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
      // 抢购交易信息表格数据
      dealinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsId: null,
        dealFrom: null,
        dealTo: null,
        handleOrderId: null,
        dealTime: null,
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
  },
  methods: {
    /** 查询抢购交易信息列表 */
    getList() {
      this.loading = true;
      listDealinfo(this.queryParams).then(response => {
        this.dealinfoList = response.rows;
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
        dealId: null,
        userId: null,
        deptId: null,
        goodsId: null,
        dealFrom: null,
        dealTo: null,
        dealNum: null,
        dealType: null,
        handleOrderId: null,
        dealTime: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
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
      this.ids = selection.map(item => item.dealId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加抢购交易信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const dealId = row.dealId || this.ids
      getDealinfo(dealId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改抢购交易信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.dealId != null) {
            updateDealinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDealinfo(this.form).then(response => {
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
      const dealIds = row.dealId || this.ids;
      this.$modal.confirm('是否确认删除抢购交易信息编号为"' + dealIds + '"的数据项？').then(function() {
        return delDealinfo(dealIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('rushsale/dealinfo/export', {
        ...this.queryParams
      }, `dealinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
