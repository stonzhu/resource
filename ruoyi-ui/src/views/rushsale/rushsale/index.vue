<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="抢购人购买id" prop="purchaseId">
        <el-select v-model="queryParams.purchaseId" placeholder="请选择抢购人购买id" clearable>
          <el-option
            v-for="dict in dict.type.pro_rushsale_buyfrom"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="收购商结算时间" prop="payTime">
        <el-date-picker clearable
          v-model="queryParams.payTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择收购商结算时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="收购商" prop="payFrom">
        <el-input
          v-model="queryParams.payFrom"
          placeholder="请输入收购商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="结算方式" prop="payType">
        <el-select v-model="queryParams.payType" placeholder="请选择结算方式" clearable>
          <el-option
            v-for="dict in dict.type.pro_rushsale_balance"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="结算状态" prop="dealState">
        <el-select v-model="queryParams.dealState" placeholder="请选择结算状态" clearable>
          <el-option
            v-for="dict in dict.type.pro_rush_dealstate"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="转送状态 转出 退回" prop="rushState">
        <el-select v-model="queryParams.rushState" placeholder="请选择转送状态 转出 退回" clearable>
          <el-option
            v-for="dict in dict.type.pro_rush_orderstate"
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
          v-hasPermi="['rushsale:rushsale:add']"
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
          v-hasPermi="['rushsale:rushsale:edit']"
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
          v-hasPermi="['rushsale:rushsale:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['rushsale:rushsale:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="rushsaleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="收购商结算id" align="center" prop="rushsaleId" />
      <el-table-column label="抢购人购买id" align="center" prop="purchaseId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pro_rushsale_buyfrom" :value="scope.row.purchaseId"/>
        </template>
      </el-table-column>
      <el-table-column label="收购商结算价" align="center" prop="payPrice" />
      <el-table-column label="收购商结算时间" align="center" prop="payTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.payTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="收购商" align="center" prop="payFrom" />
      <el-table-column label="结算方式" align="center" prop="payType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pro_rushsale_balance" :value="scope.row.payType"/>
        </template>
      </el-table-column>
      <el-table-column label="中间价" align="center" prop="dealPrice" />
      <el-table-column label="结算状态" align="center" prop="dealState">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pro_rush_dealstate" :value="scope.row.dealState"/>
        </template>
      </el-table-column>
      <el-table-column label="转送状态 转出 退回" align="center" prop="rushState">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pro_rush_orderstate" :value="scope.row.rushState"/>
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
            v-hasPermi="['rushsale:rushsale:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['rushsale:rushsale:remove']"
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

    <!-- 添加或修改出售交易信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="部门id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入部门id" />
        </el-form-item>
        <el-form-item label="抢购人购买id" prop="purchaseId">
          <el-select v-model="form.purchaseId" placeholder="请选择抢购人购买id">
            <el-option
              v-for="dict in dict.type.pro_rushsale_buyfrom"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="收购商结算价" prop="payPrice">
          <el-input v-model="form.payPrice" placeholder="请输入收购商结算价" />
        </el-form-item>
        <el-form-item label="收购商结算时间" prop="payTime">
          <el-date-picker clearable
            v-model="form.payTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择收购商结算时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="收购商" prop="payFrom">
          <el-input v-model="form.payFrom" placeholder="请输入收购商" />
        </el-form-item>
        <el-form-item label="结算方式" prop="payType">
          <el-select v-model="form.payType" placeholder="请选择结算方式">
            <el-option
              v-for="dict in dict.type.pro_rushsale_balance"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="中间价" prop="dealPrice">
          <el-input v-model="form.dealPrice" placeholder="请输入中间价" />
        </el-form-item>
        <el-form-item label="结算状态" prop="dealState">
          <el-select v-model="form.dealState" placeholder="请选择结算状态">
            <el-option
              v-for="dict in dict.type.pro_rush_dealstate"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="转送状态 转出 退回" prop="rushState">
          <el-select v-model="form.rushState" placeholder="请选择转送状态 转出 退回">
            <el-option
              v-for="dict in dict.type.pro_rush_orderstate"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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
import { listRushsale, getRushsale, delRushsale, addRushsale, updateRushsale } from "@/api/rushsale/rushsale";

export default {
  name: "Rushsale",
  dicts: ['pro_rushsale_buyfrom', 'pro_rushsale_balance', 'pro_rush_orderstate', 'pro_rush_dealstate'],
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
      // 出售交易信息表格数据
      rushsaleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        purchaseId: null,
        payTime: null,
        payFrom: null,
        payType: null,
        dealState: null,
        rushState: null,
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
    /** 查询出售交易信息列表 */
    getList() {
      this.loading = true;
      listRushsale(this.queryParams).then(response => {
        this.rushsaleList = response.rows;
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
        rushsaleId: null,
        userId: null,
        deptId: null,
        purchaseId: null,
        payPrice: null,
        payTime: null,
        payFrom: null,
        payType: null,
        dealPrice: null,
        dealState: null,
        rushState: null,
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
      this.ids = selection.map(item => item.rushsaleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加出售交易信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const rushsaleId = row.rushsaleId || this.ids
      getRushsale(rushsaleId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改出售交易信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.rushsaleId != null) {
            updateRushsale(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRushsale(this.form).then(response => {
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
      const rushsaleIds = row.rushsaleId || this.ids;
      this.$modal.confirm('是否确认删除出售交易信息编号为"' + rushsaleIds + '"的数据项？').then(function() {
        return delRushsale(rushsaleIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('rushsale/rushsale/export', {
        ...this.queryParams
      }, `rushsale_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
