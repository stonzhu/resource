<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品名称" prop="goodsName">
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
      <el-form-item label="数量" prop="remain">
        <el-input
          v-model="queryParams.remain"
          placeholder="查询大于该数量的数据"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          :disabled="single"
          @click="handleReceive"
          v-hasPermi="['goodsStatis:goodsStatis:receive']"
        >领取</el-button>
      </el-col>
<!--      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['goodsStatis:goodsStatis:add']"
        >新增库存</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['goodsStatis:goodsStatis:edit']"
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
          v-hasPermi="['goodsStatis:goodsStatis:remove']"
        >删除</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['goodsStatis:goodsStatis:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="goodsStatisList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="goodsStatisId" v-if="false"/>
      <el-table-column label="商品ID" align="center" prop="goodsId" v-if="false"/>
      <el-table-column label="商品名" align="center" prop="goodsName" />
      <el-table-column label="规格型号" align="center" prop="normsModel" />
      <el-table-column label="总数量" align="center" prop="total" />
      <el-table-column label="剩余数量" align="center" prop="remain" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['goodsStatis:goodsStatis:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleReceive(scope.row)"
            v-hasPermi="['goodsStatis:goodsStatis:receive']"
          >领取</el-button>
<!--          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['goodsStatis:goodsStatis:remove']"
          >删除</el-button>-->
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

    <!-- 添加或修改商品库存动态对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品ID" prop="goodsId" v-if="false">
          <el-input v-model="form.goodsId" placeholder="请输入商品ID" />
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请选择商品" />
        </el-form-item>
        <el-form-item label="规格型号" prop="normsModel">
          <el-input v-model="form.normsModel" placeholder="请选择规格型号" />
        </el-form-item>
        <el-form-item label="总数量" prop="total">
          <el-input v-model="form.total" placeholder="请输入总数量" />
        </el-form-item>
        <el-form-item label="剩余数量" prop="remain">
          <el-input v-model="form.remain" placeholder="请输入剩余数量" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改领用记录对话框 -->
    <el-dialog :title="title" :visible.sync="openReceive" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="receiverules" label-width="80px">
        <el-form-item label="商品ID" prop="goodsId" v-if="false">
          <el-input v-model="form.goodsId" />
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName" >
          <el-input disabled v-model="form.goodsName" placeholder="请选择商品" />
        </el-form-item>
        <el-form-item label="规格型号" prop="normsModel" disabled>
          <el-input disabled v-model="form.normsModel" placeholder="请输入规格型号" />
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitReceiveForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加或修改交还记录对话框 -->
    <el-dialog :title="title" :visible.sync="openReturn" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品ID" prop="goodsId">
          <el-input v-model="form.goodsId" placeholder="请输入商品ID" />
        </el-form-item>
        <el-form-item label="规格型号" prop="normsModel">
          <el-input v-model="form.normsModel" placeholder="请输入规格型号" />
        </el-form-item>
        <el-form-item label="交还数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入交还数量" />
        </el-form-item>
        <el-form-item label="交还人" prop="returnPeople">
          <el-input v-model="form.returnPeople" placeholder="请输入交还人" />
        </el-form-item>
        <el-form-item label="交还时间" prop="returnTime">
          <el-date-picker clearable
                          v-model="form.returnTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择交还时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="领用人" prop="recipient">
          <el-input v-model="form.recipient" placeholder="请输入领用人" />
        </el-form-item>
        <el-form-item label="交接办理人" prop="handover">
          <el-input v-model="form.handover" placeholder="请输入交接办理人" />
        </el-form-item>
        <el-form-item label="交还原因" prop="reason">
          <el-input v-model="form.reason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRetunForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listGoodsStatis, getGoodsStatis, delGoodsStatis, addGoodsStatis, updateGoodsStatis } from "@/api/wms/goodsStatis";
import {  addReceiveRecord,getReceiveRecord } from "@/api/wms/receiveRecord";
export default {
  name: "GoodsStatis",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      goodsIds: [],
      goodsNum:[],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 商品库存动态表格数据
      goodsStatisList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //是否弹出领用页面
      openReceive:false,
      //是否弹出交还页面
      openReturn:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsName: null,
        normsModel: null,
        remain: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        goodsId: [
          { required: true, message: "商品ID不能为空", trigger: "blur" }
        ],
        goodsName: [
          { required: true, message: "商品名不能为空", trigger: "change" }
        ],
        normsModel: [
          { required: true, message: "规格型号不能为空", trigger: "blur" }
        ],
        total: [
          { required: true, message: "总数量不能为空", trigger: "blur" }
        ],
        remain: [
          { required: true, message: "剩余数量不能为空", trigger: "blur" }
        ]
      },
      receiverules:{
        quantity: [
          { required: true, message: "领取数量不能为空", trigger: "blur" }
        ],
        recipient: [
          { required: true, message: "领用人不能为空", trigger: "blur" }
        ],
        receiveTime: [
          { required: true, message: "领用时间不能为空", trigger: "blur" }
        ],
        handover:[
          { required: true, message: "交接办理人不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询商品库存动态列表 */
    getList() {
      this.loading = true;
      listGoodsStatis(this.queryParams).then(response => {
        this.goodsStatisList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.openReceive = false;
      this.openReturn = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        goodsStatisId: null,
        goodsId: null,
        goodsName: null,
        normsModel: null,
        total: null,
        remain: null,
        //领取数据
        quantity: null,
        recipient: null,
        department: null,
        receiveTime: null,
        useTo: null,
        approver: null,
        handover: null
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
      this.ids = selection.map(item => item.goodsStatisId)
      this.goodsIds = selection.map(item => item.goodsId)
      this.goodsNum =  selection.map(item => item.remain)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.openReceive = false;
      this.openReturn = false;
      this.open = true;
      this.title = "添加商品库存动态";
    },

    handleReceive(row){
      this.reset();
      this.open = false;
      this.openReturn = false;
      const goodsStatisId = row.goodsStatisId || this.ids
      getGoodsStatis(goodsStatisId).then(response => {
        this.form = response.data;
        this.openReceive = true;
        this.title = "领取商品";
      });
    },
    //领取商品的功能
    submitReceiveForm(){
      var receiveNum= this.form.quantity;
      var remainNum= this.goodsNum[0];
      if(remainNum>=receiveNum){
        addReceiveRecord(this.form).then(response => {
          this.$modal.msgSuccess("领取成功");
          this.openReceive = false;
          this.getList();
        });
      }else{
        this.$modal.msgError("领取数量不能大于库存数量");
      }

    },
    submitRetunForm(){},
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const goodsStatisId = row.goodsStatisId || this.ids
      getGoodsStatis(goodsStatisId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品库存动态";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.goodsStatisId != null) {
            updateGoodsStatis(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGoodsStatis(this.form).then(response => {
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
      const goodsStatisIds = row.goodsStatisId || this.ids;
      this.$modal.confirm('是否确认删除商品库存动态编号为"' + goodsStatisIds + '"的数据项？').then(function() {
        return delGoodsStatis(goodsStatisIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('goodsStatis/goodsStatis/export', {
        ...this.queryParams
      }, `goodsStatis_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
