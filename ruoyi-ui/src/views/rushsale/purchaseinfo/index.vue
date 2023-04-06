<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="purchName">
        <el-input
          v-model="queryParams.purchName"
          placeholder="请输入购买人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phoneNum">
        <el-input
          v-model="queryParams.phoneNum"
          placeholder="请输入购买人手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="购买渠道" prop="buyFrom">
        <el-select v-model="queryParams.buyFrom" placeholder="请选择购买渠道" clearable>
          <el-option
            v-for="dict in dict.type.pro_rushsale_buyfrom"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="下单时间" prop="buyTime">
        <el-date-picker clearable
          v-model="queryParams.buyTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择下单时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="收货人" prop="orderNum">
        <el-input
          v-model="queryParams.orderNum"
          placeholder="请选择收货人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="机器码" prop="machineId">
        <el-input
          v-model="queryParams.machineId"
          placeholder="请输入机器唯一码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单状态" prop="orderState">
        <el-select v-model="queryParams.orderState" placeholder="请选择订单状态" clearable>
          <el-option
            v-for="dict in dict.type.pro_rush_orderstate"
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
      <el-form-item label="到货时间" prop="arrivalTime">
        <el-date-picker clearable
          v-model="queryParams.arrivalTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择到货时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结算时间" prop="dealTime">
        <el-date-picker clearable
          v-model="queryParams.dealTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择结算时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="流转状态" prop="rushState">
        <el-select v-model="queryParams.rushState" placeholder="请选择抢购流转状态 发货 到货 退货" clearable>
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
          v-hasPermi="['rushsale:purchaseinfo:add']"
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
          v-hasPermi="['rushsale:purchaseinfo:edit']"
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
          v-hasPermi="['rushsale:purchaseinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['rushsale:purchaseinfo:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-edit"
          size="mini"
          @click="handleCopy"
          v-hasPermi="['rushsale:purchaseinfo:add']"
        >复制一行</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="purchaseinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="purchaseId" v-if="false"/>
      <el-table-column label="购买人姓名id" align="center" prop="purchNameId" v-if="false"/>
      <el-table-column label="购买人姓名" align="center" prop="purchName"/>

      <el-table-column label="商品名称" align="center" prop="goodsName"/>
      <el-table-column label="商品品牌" align="center" prop="goodsBrand"/>
      <el-table-column label="商品型号" align="center" prop="goodsModel"/>
      <el-table-column label="商品颜色" align="center" prop="goodsColor"/>

      <el-table-column label="购买渠道" align="center" prop="buyFrom" >
        <template slot-scope="scope">
          <el-select v-model="scope.row.buyFrom" @change="updateRow(scope.row,'buyFrom')"
                     size="mini">
            <el-option
              v-for="dict in dict.type.pro_rushsale_buyfrom"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="购买价格" align="center" prop="buyPrice">
        <template slot-scope="scope">
          <el-input v-model="scope.row.buyPrice" @change="updateRow(scope.row,'buyFrom')"/>
        </template>
      </el-table-column>

      <el-table-column label="下单时间" align="center" prop="buyTime" width="180">
        <template slot-scope="scope">
          <el-date-picker clearable
                          v-model="scope.row.buyTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          @change="updateRow(scope.row,'buyTime')">
          </el-date-picker>
        </template>
      </el-table-column>
      <el-table-column label="收货人" align="center" prop="orderNum" cell-style="1000px">
        <template slot-scope="scope">
          <el-select v-model="scope.row.orderNum" @change="updateRow(scope.row,'orderNum')">
            <el-option
              v-for="buyPeo in buyNameList"
              :key="buyPeo.userId"
              :label="buyPeo.userName"
              :value="buyPeo.userId"
            ></el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column label="订单状态" align="center" prop="orderState" >
        <template slot-scope="scope">
          <el-select v-model="scope.row.orderState" @change="updateRow(scope.row,'orderState')" >
            <el-option
              v-for="dict in dict.type.pro_rush_orderstate"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="结算状态" align="center" prop="dealState">
        <template slot-scope="scope">
          <el-select v-model="scope.row.dealState" @change="updateRowConfirm(scope.row,'dealState')" >
            <el-option
              v-for="dict in dict.type.pro_rush_dealstate"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="到货时间" align="center" prop="arrivalTime" width="180">
        <template slot-scope="scope">
          <el-date-picker clearable
                          v-model="scope.row.arrivalTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          @change="updateRow(scope.row,'arrivalTime')">
          </el-date-picker>
        </template>
      </el-table-column>
      <el-table-column label="结算价" align="center" prop="dealPrice">
        <template slot-scope="scope">
          <el-input v-model="scope.row.dealPrice" @change="updateRow(scope.row,'dealPrice')"/>
        </template>
      </el-table-column>
      <el-table-column label="结算时间" align="center" prop="dealTime" width="180">
        <template slot-scope="scope">
          <el-date-picker clearable
                          v-model="scope.row.arrivalTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          @change="updateRow(scope.row,'dealTime')">
          </el-date-picker>
        </template>
      </el-table-column>
      <el-table-column label="运单号" align="center" prop="remark">
        <template slot-scope="scope">
          <el-input v-model="scope.row.remark" @change="updateRow(scope.row,'remark')"/>
        </template>
      </el-table-column>
      <el-table-column label="机器唯一码" align="center" prop="machineId">
        <template slot-scope="scope">
          <el-input v-model="scope.row.machineId" @change="updateRow(scope.row,'machineId')"/>
        </template>
      </el-table-column>
      <el-table-column label="抢购流转状态" align="center" prop="rushState">
        <template slot-scope="scope">
          <el-select v-model="scope.row.rushState" @change="updateRow(scope.row,'rushState')" >
            <el-option
              v-for="dict in dict.type.pro_rush_orderstate"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="购买人手机号" align="center" prop="phoneNum" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleCopy(scope.row.purchaseId)"
            v-hasPermi="['rushsale:purchaseinfo:edit']"
          >新增一行</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['rushsale:purchaseinfo:remove']"
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

    <!-- 添加或修改抢购人购买结算信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="部门id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入部门id" />
        </el-form-item>
        <el-form-item label="购买人姓名" prop="purchName">
          <el-select v-model="form.purchName" filterable placeholder="请选择购买人姓名">
            <el-option
              v-for="item in purchNameList"
              :key="item.userId"
              :label="item.userName"
              :value="item.userId">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="购买商品" prop="buyFrom">
          <el-select v-model="form.goodsId" placeholder="请选择商品">
            <el-option
              v-for="goods in goodsList"
              :key="goods.goodsId"
              :label="goods.name+goods.brand+goods.model+goods.color"
              :value="goods.goodsId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="购买人手机号" prop="phoneNum">
          <el-input v-model="form.phoneNum" placeholder="请输入购买人手机号" />
        </el-form-item>
        <el-form-item label="购买渠道" prop="buyFrom">
          <el-select v-model="form.buyFrom" placeholder="请选择购买渠道">
            <el-option
              v-for="dict in dict.type.pro_rushsale_buyfrom"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="购买价格" prop="buyPrice">
          <el-input v-model="form.buyPrice" placeholder="请输入购买价格" />
        </el-form-item>
        <el-form-item label="下单时间" prop="buyTime">
          <el-date-picker clearable
            v-model="form.buyTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择下单时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="运单号" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入运单号" />
        </el-form-item>
        <el-form-item label="收货人" prop="orderNum">
          <el-select v-model="form.orderNum" placeholder="请选择收货人">
            <el-option
              v-for="buyPeo in buyNameList"
              :key="buyPeo.userId"
              :label="buyPeo.userName"
              :value="buyPeo.userId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="机器唯一码" prop="machineId">
          <el-input v-model="form.machineId" placeholder="请输入机器唯一码" />
        </el-form-item>
        <el-form-item label="订单状态" prop="orderState">
          <el-select v-model="form.orderState" placeholder="请选择订单状态">
            <el-option
              v-for="dict in dict.type.pro_rush_orderstate"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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
        <el-form-item label="到货时间" prop="arrivalTime">
          <el-date-picker clearable
            v-model="form.arrivalTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择到货时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结算价" prop="dealPrice">
          <el-input v-model="form.dealPrice" placeholder="请输入结算价" />
        </el-form-item>
        <el-form-item label="结算时间" prop="dealTime">
          <el-date-picker clearable
            v-model="form.dealTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择结算时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="抢购流转状态" prop="rushState">
          <el-select v-model="form.rushState" placeholder="请选择抢购流转状态 发货 到货 退货">
            <el-option
              v-for="dict in dict.type.pro_rush_orderstate"
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

    <!-- 选择收货人对话框 -->
    <el-dialog :title="titleB" :visible.sync="openB" width="500px" append-to-body>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPurchaseinfo,listPurchase, getPurchaseinfo, delPurchaseinfo, addPurchaseinfo, updatePurchaseinfo,handleCopy } from "@/api/rushsale/purchaseinfo";
import {getUser} from "@/api/system/user";
import {listGoods} from "@/api/rushsale/goods";

export default {
  name: "Purchaseinfo",
  dicts: ['pro_rushsale_buyfrom', 'pro_rush_orderstate', 'pro_rush_dealstate'],
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
      // 抢购人购买结算信息表格数据
      purchaseinfoList: [],
      //商品数据下拉框
      goodsList: [],
      //购买人下拉列表
      purchNameList: [],
      //收货人下拉列表
      buyNameList: [],
      //商品数据map
      goodsMap: [],
      // 弹出层标题
      title: "",
      titleB: "",
      // 是否显示弹出层
      open: false,
      openB: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        purchName: null,
        phoneNum: null,
        goodsId: null,
        buyFrom: null,
        buyTime: null,
        orderNum: null,
        machineId: null,
        orderState: null,
        dealState: null,
        arrivalTime: null,
        dealTime: null,
        rushState: null,
      },
      // 表单参数
      form: {},
      formB: {},
      // 表单校验
      rules: {
        remark: [
          { required: true, message: "运单号不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getXialaList();
    this.getList();
  },
  methods: {
    /** 查询抢购人购买结算信息列表 */
    getList() {
      this.loading = true;
      listPurchaseinfo(this.queryParams).then(response => {
        this.purchaseinfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //获取列表数据
    getXialaList(){
      listPurchase().then(response => {
        //过滤用户
        var index;
        for(index in response.buyNameList){
          this.buyNameList.push({"userId":response.buyNameList[index].userId+'',
            "userName":response.buyNameList[index].userName});
          this.purchNameList.push({"userId":response.buyNameList[index].userId+'',
            "userName":response.buyNameList[index].userName})
        }
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
        purchaseId: null,
        userId: null,
        deptId: null,
        purchName: null,
        phoneNum: null,
        goodsId: null,
        buyFrom: null,
        buyPrice: null,
        buyTime: null,
        orderNum: null,
        machineId: null,
        orderState: null,
        dealState: null,
        arrivalTime: null,
        dealPrice: null,
        dealTime: null,
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
      this.ids = selection.map(item => item.purchaseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      listPurchase().then(response => {
          this.goodsList = response.goodsList;
          //this.purchNameList = response.purchNameList;
          //this.buyNameList = response.buyNameList;
          this.open = true;
          this.title = "添加抢购人购买信息";
        });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const purchaseId = row.purchaseId || this.ids
      getPurchaseinfo(purchaseId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改抢购人购买结算信息";
      });
    },
    /** 复制一行操作 */
    handleCopy(purchaseId) {
      handleCopy(purchaseId).then(response => {
        this.getList();
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.purchaseId != null) {
            updatePurchaseinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPurchaseinfo(this.form).then(response => {
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
      const purchaseIds = row.purchaseId || this.ids;
      this.$modal.confirm('是否确认删除抢购人购买结算信息编号为"' + purchaseIds + '"的数据项？').then(function() {
        return delPurchaseinfo(purchaseIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('rushsale/purchaseinfo/export', {
        ...this.queryParams
      }, `purchaseinfo_${new Date().getTime()}.xlsx`)
    },

    /** 修改行信息 */
    updateRow(row,type){
      row.type = type;
      row.purchName =row.purchNameId;
      updatePurchaseinfo(row).then(response => {
        //this.$modal.msgSuccess("修改成功");
        this.getList();
      });
    },
    updateRowConfirm(row,type) {
      this.$confirm('是否继续修改?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        row.type = type;
        row.purchName =row.purchNameId;
        //校验数据，结算价不能为空
        if(!row.dealPrice || row.dealPrice <0 ){
          this.$message({
            type: 'info',
            message: '结算价不能为空'
          });
          this.getList();
          return;
        }
        updatePurchaseinfo(row).then(response => {
          //this.$modal.msgSuccess("修改成功");
          this.getList();
        });
        this.$message({
          type: 'success',
          message: '修改成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消修改'
        });
      });
    }
  },

};
</script>
